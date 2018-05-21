package net.sam.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.sam.shoppingbackend.dao.ProductDAO;
import net.sam.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO poductDao;
	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.sam.shoppingbackend");
		context.refresh();
		poductDao = (ProductDAO) context.getBean("ProductDAO");
	}
	
	/*@Test
	public void testCRUDProduct() {
		
		product = new Product();
		product.setName("Oppo selfie s53");
		product.setBrand("Oppo");
		product.setDescription("This is some descriptin about Oppo Mobile");
		product.setUnitprice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		assertEquals("Something went wrong while adding a Category inside Table", true, poductDao.add(product));
		
		product = poductDao.get(2);
		product.setName("Samsung Galaxy s7");
		assertEquals("Something went wrong while updating Product inside Table", true, poductDao.update(product));
		
		assertEquals("Something went wrong while Deleting Product existing record", true, poductDao.delete(product));
		
		assertEquals("Something went wrong while fetching the Product List",6,poductDao.list().size());
	}*/
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the Product List",5,poductDao.listActiveProducts().size());
	}
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the Product List",3,poductDao.
				listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the Product List",2,poductDao.
				listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testLatestActiveProducts() {
		assertEquals("Something went wrong while fetching the Product List",3,poductDao.
				getLatestActiveProducts(3).size());
	}
}
