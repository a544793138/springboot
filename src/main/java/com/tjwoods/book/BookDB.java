package com.tjwoods.book;

import java.util.HashMap;
import java.util.Map;

public class BookDB {

	public final static Map<Integer, Book> BOOKS = new HashMap<>();
	
	static {
		BOOKS.put(1, new Book(1, "book1", "TJwoods"));
		BOOKS.put(2, new Book(2, "book2", "Liliyard"));
		BOOKS.put(3, new Book(3, "book3", "yueban"));
		BOOKS.put(4, new Book(4, "book4", "zhongqiu"));
	}
	
}
