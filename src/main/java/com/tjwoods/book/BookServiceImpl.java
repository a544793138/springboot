package com.tjwoods.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Cacheable(value = "bookCache", key = "#name")
	@Override
	public Book findByName(String name) {
		log.info("find book {} from DB.", name);
		return new Book(1, name, "writer");
	}

	@CachePut(value = "bookCache", key = "#book.name")
	@Override
	public Book update(Book book) throws Exception {
		log.info("Update {} to DB.", book);
		return book;
	}

}
