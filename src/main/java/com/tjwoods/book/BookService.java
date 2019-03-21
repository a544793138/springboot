package com.tjwoods.book;

public interface BookService {

	Book findByName(String name) throws Exception;

	Book update(Book book) throws Exception;

}
