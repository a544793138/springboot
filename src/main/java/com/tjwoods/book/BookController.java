package com.tjwoods.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	private final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	@GetMapping("/book/{name}")
	public Book findByName(@PathVariable("name") String name) throws Exception {
		log.info("Controller get request for find book by name: {}", name);
		return bookService.findByName(name);
	}

	@PutMapping("/book/update")
	public Book updateBookById(@RequestBody Book book) throws Exception {
		return bookService.update(book);
	}
	
}
