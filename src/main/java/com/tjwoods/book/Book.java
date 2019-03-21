package com.tjwoods.book;

import java.io.Serializable;

public class Book implements Serializable {

	// 缓存同步需要序列化实体类
	private static final long serialVersionUID = 1549832281504335191L;

	private Integer id;

	private String name;

	private String writer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Book(Integer id, String name, String writer) {
		this.id = id;
		this.name = name;
		this.writer = writer;
	}

	public Book() {
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", writer=" + writer + "]";
	}

}
