package com.tjwoods.book;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryInvalidated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryInvalidatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Listener
public class BookListener {

	private final Logger log = LoggerFactory.getLogger(BookListener.class);

	@CacheEntryCreated
	public void observeAdd(CacheEntryCreatedEvent<String, String> event) {
		if (event.isPre())
			return;
		log.info("Cache entry {} = {} added in cache {}", event.getKey(), event.getValue(), event.getCache());
	}

	@CacheEntryModified
	public void observeUpdate(CacheEntryModifiedEvent<String, String> event) {
		if (event.isPre())
			return;
		log.info("Cache entry {} = {} modified in cache {}", event.getKey(), event.getValue(), event.getCache());
	}

	@CacheEntryRemoved
	public void observeRemove(CacheEntryRemovedEvent<String, String> event) {
		if (event.isPre())
			return;
		log.info("Cache entry {} removed in cache {}", event.getKey(), event.getCache());
	}

	@CacheEntryInvalidated
	public void observeInvalidated(CacheEntryInvalidatedEvent<String, String> event) {
		if (event.isPre())
			return;
		log.info("Cache entry {} Invalidated in cache {}", event.getKey(), event.getCache());
	}
	
}
