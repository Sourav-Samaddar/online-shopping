package net.sam.shoppingbackend.dao;

import java.util.List;

import net.sam.shoppingbackend.dto.Category;

public interface CategoryDao {

	public List<Category> list();
	public Category get(int id);
}
