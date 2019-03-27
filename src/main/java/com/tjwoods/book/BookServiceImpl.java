package com.tjwoods.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Cacheable(value = "bookCache", key = "#id")
	@Override
	public Book findById(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' cannot be null");
		}
		if (!BookDB.BOOKS.containsKey(id)) {
			log.debug("Cannot found book by id: {} from BOOKDB", id);
			return null;
		}
		return BookDB.BOOKS.get(id);
	}

	/**
	 * 
	 */
	@CachePut(value = "bookCache", key = "#id")
	@Override
	public Book update(Integer id, Book book) {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' cannot be null");
		}
		if (book == null) {
			throw new IllegalArgumentException("Parameter 'book' cannot be null");
		}
		if (!BookDB.BOOKS.containsKey(id)) {
			log.debug("Cannot found book by id: {} from BOOKDB", id);
			return null;
		}
		BookDB.BOOKS.put(id, book);
		return book;
	}

}
