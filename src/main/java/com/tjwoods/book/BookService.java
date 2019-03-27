package com.tjwoods.book;

public interface BookService {

	/**
	 * 通过 Id 查询 book
	 * 
	 * @param id
	 * @return
	 */
	Book findById(Integer id);

	/**
	 * 通过 Id 更新 book
	 * 
	 * @param id
	 *            需要更新的 book 的 id
	 * @param book
	 *            将该 book 对象更新到 BOOKDB中
	 * @return 更新后的 book
	 */
	Book update(Integer id, Book book);

}
