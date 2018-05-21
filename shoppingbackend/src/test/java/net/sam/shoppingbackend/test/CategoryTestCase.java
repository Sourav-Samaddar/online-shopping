package net.sam.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.sam.shoppingbackend.dao.CategoryDao;
import net.sam.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDao categoryDao;
	private Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.sam.shoppingbackend");
		context.refresh();
		categoryDao = (CategoryDao) context.getBean("categoryDao");
	}
	
	/*@Test
	public void testAddCategory() {
		Category category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some description of Laptop");
		category.setImageURL("CAT_3.png");
		assertEquals("Succesfully Added a Category inside Table", true, categoryDao.add(category));
	}*/
	
	/*@Test
	public void testGetCategory() {
		Category category = categoryDao.get(1);
		assertEquals("Succesfully fetched a single Category inside Table", "Television", category.getName());
	}*/
	
	/*@Test
	public void testUpdateCategory() {
		Category category = categoryDao.get(1);
		category.setName("TV");
		assertEquals("Succesfully updated a single Category inside Table", true, categoryDao.update(category));
	}*/
	
	/*@Test
	public void testDeleteCategory() {
		Category category = categoryDao.get(1);
		assertEquals("Succesfully deleted a single Category inside Table", true, categoryDao.delete(category));
	}*/
	
	/*@Test
	public void testListCategory() {
		assertEquals("Succesfully fetch list of Category inside Table", 2, categoryDao.list().size());
	}*/
	
	@Test
	public void testCRUDOperation() {
		
		//Adding Category
		Category category = new Category();
		category.setName("Laptop");
		category.setDescription("This is some description of Laptop");
		category.setImageURL("CAT_1.png");
		assertEquals("Succesfully Added a Category inside Table", true, categoryDao.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("This is some description of Television");
		category.setImageURL("CAT_2.png");
		assertEquals("Succesfully Added a Category inside Table", true, categoryDao.add(category));
		
		//Fetching and Updating Category
		category = categoryDao.get(2);
		category.setName("TV");
		assertEquals("Succesfully updated a single Category inside Table", true, categoryDao.update(category));
		
		//Delete Category
		assertEquals("Succesfully deleted a single Category inside Table", true, categoryDao.delete(category));
		
		//Get list of active category
		assertEquals("Succesfully fetch list of Category inside Table", 1, categoryDao.list().size());
	}
}
