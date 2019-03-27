package com.tjwoods.book;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/book/{id}")
	public Map<String, Object> findByName(@PathVariable("id") Integer id) {
		Map<String, Object> result = new HashMap<>();
		try {
			Book book = bookService.findById(id);
			if (book != null) {
				result.put("status", "success");
				result.put("book", book);
			} else {
				result.put("status", "not found");
				result.put("message", "Cannot found book by id: " + id);
			}
		} catch (Exception e) {
			result.put("status", "error");
			result.put("message", e.getMessage());
		}
		return result;
	}

	@PutMapping("/book/{id}")
	public Map<String, Object> updateBookById(@PathVariable("id") Integer id, @RequestBody Book book) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {
			Book updateBook = bookService.update(id, book);
			if (updateBook != null) {
				result.put("status", "success");
				result.put("book", book);
			} else {
				result.put("status", "not found");
				result.put("message", "Cannot found book by id: " + id);
			}
		} catch (Exception e) {
			result.put("status", "error");
			result.put("message", e.getMessage());
		}
		return result;
	}

}
