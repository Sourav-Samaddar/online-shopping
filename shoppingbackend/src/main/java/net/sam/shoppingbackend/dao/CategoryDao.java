package net.sam.shoppingbackend.dao;

import java.util.List;

import net.sam.shoppingbackend.dto.Category;

public interface CategoryDao {

	public List<Category> list();
	public Category get(int id);
	public boolean add(Category category);
	public boolean update(Category category);
	public boolean delete(Category category);
}
