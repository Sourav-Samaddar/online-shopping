package net.sam.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.sam.shoppingbackend.dao.CategoryDao;
import net.sam.shoppingbackend.dto.Category;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	private static List<Category> categories = new ArrayList<Category>();
	
	static {
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description of Television");
		category.setImageURL("CAT_1.png");
		categories.add(category);
		
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some description of Mobile");
		category.setImageURL("CAT_2.png");
		categories.add(category);
		
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is some description of Laptop");
		category.setImageURL("CAT_3.png");
		categories.add(category);
	}
	
	@Override
	public List<Category> list() {
		return categories;
	}

	@Override
	public Category get(int id) {
		for(Category category: categories) {
			if(category.getId() == id) {
				return category;
			}
		}
		return null;
	}

}