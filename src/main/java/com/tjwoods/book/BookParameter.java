package com.tjwoods.book;

public class BookParameter {

	private Integer id;

	private String oldBookName;

	private Book updateBook;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldBookName() {
		return oldBookName;
	}

	public void setOldBookName(String oldBookName) {
		this.oldBookName = oldBookName;
	}

	public Book getUpdateBook() {
		return updateBook;
	}

	public void setUpdateBook(Book updateBook) {
		this.updateBook = updateBook;
	}

}
