package com.tjwoods.cache;

import org.apache.commons.lang3.StringUtils;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.global.TransportConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanConfigurationCustomizer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tjwoods.book.BookListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
public class InfinispanCacheConfig {

	/**
	 * 集群名称
	 */
	@Value("${cluster.cluster-name: }")
	private String clusterName;

	/**
	 * 本地节点名称
	 */
	@Value("${cluster.node-name: }")
	private String nodeName;

	/**
	 * 通讯协议：TCP、UDP、K8S，默认 UDP
	 */
	@Value("${cluster.protocol: UDP}")
	private String protocol;

	private final static String TCP_PROTOCOL = "default-jgroups-tcp.xml";
	private final static String UDP_PROTOCOL = "default-jgroups-udp.xml";
	private final static String K8S_PROTOCOL = "default-jgroups-kubernetes.xml";

	// 默认的缓存模式配置
	@Bean
	public InfinispanConfigurationCustomizer configurationCustomizer() {
		return builder -> builder.clustering().cacheMode(CacheMode.REPL_SYNC).build();
	}

	// 特定的缓存模式配置，用于添加Listener
	@Bean
	public InfinispanCacheConfigurer infinispanCacheConfigurer() {
		return manager -> {
			manager.getCache("bookCache").addListener(new BookListener());
		};
	}

	@Bean
	public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
		return builder -> {
			TransportConfigurationBuilder transportConfigurationBuilder = builder.transport().defaultTransport();

			String useProperty = "";
			switch (protocol.toLowerCase()) {
			case "tcp": {
				useProperty = TCP_PROTOCOL;
				break;
			}
			case "k8s": {
				useProperty = K8S_PROTOCOL;
				break;
			}
			default: {
				useProperty = UDP_PROTOCOL;
			}
			}
			transportConfigurationBuilder.addProperty("configurationFile", useProperty);
			log.info("Infinispan use protocol properties file: {}", useProperty);

			if (StringUtils.isNotBlank(clusterName)) {
				transportConfigurationBuilder.clusterName(clusterName);
				log.info("Infinispan set cluster name: {}", clusterName);
			}
			if (StringUtils.isNotBlank(nodeName)) {
				transportConfigurationBuilder.nodeName(nodeName);
				log.info("Infinispan set this node name: {}", nodeName);
			}
			transportConfigurationBuilder.build();
		};
	}

}
