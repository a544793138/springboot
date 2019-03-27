[TOC]

# springboot-infinispan-demo

本项目是将 infinispan 集成到 springboot 的一个 demo，主要用于演示搭建集群以及使缓存在集群节点之间同步。

## 关于 infinispan

1. demo 选择了以代码的方式对 infinispan 进行配置。

> infinispan 官方指出还支持使用 xml 格式的配置文件进行配置，有兴趣可以去了解。

2. demo 中使用的 infinispan 的缓存模式为 "同步复制模式"，我还尝试过 "失效模式"，都会在下文中说出其中的效果。
3. demo 中使用 infinispan 提供的 infinispan 与 springboot 整合的依赖。
4. 本文涉及到该 demo 运行在 docker、K8S 上的说明。

## 代码结构

- book - 以书为例子的缓存使用例子
- cache - 配置 infinispan 的地方
- application - 配置文件，可以设定启动的集群和节点名
- default-jgroups-*.xml - infinispan 中使用 jgroups 来连通节点，这些 xml 配置文件都是 jgroups 的配置文件。

## Docker 与 K8S 连通说明

在普通情况下，UDP 和 TCP 最多只能实现同主机下的多实例搭建集群和集群通讯，甚至使用 default-jgroups-K8S.xml 也是这样。需要使用类似 weave 插件覆盖网络，才可以在跨主机上进行通讯。

### docker 连通

正如前面所说，jgroups 完成了 infinispan 节点发现和通讯的工作，所以我们主要针对 jgroups 的配置文件作修改。

jgroups 的官网上也有 jgroups 在 docker 上运行的例子镜子。甚至可以在 GitHub 上找到 jgroups 为 docker 所写的 demo 源码以及相关注意事项。

需要修改配置文件

- 如果使用TCP

```xml
<TCP 
     bind_addr="match-interface:eth0,site_local,loopback" 
     ...
/>

<MPING mcast_addr="${jgroups.mping.mcast_addr:228.2.4.6}"
       mcast_port="${jgroups.mping.mcast_port:43366}"
       ip_ttl="${jgroups.udp.ip_ttl:10}" 
/>
```

- 如果使用UDP - 无需改动配置文件，但需要在docker中将多播端口暴露出来，注意暴露的端口是udp而非tcp

### K8S

```xml
<TCP external_addr="${JGROUPS_EXTERNAL_ADDR:match-interface:eth0}"
	 bind_addr="loopback,match-interface:eth0"
     bind_port="${TCP_PORT:7800}"
```



