package net.sam.onlineshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sam.shoppingbackend.dao.ProductDAO;
import net.sam.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDao;
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProduct(){
		return productDao.listActiveProducts();
	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductForAdmin(){
		return productDao.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getAllProductByCategory(@PathVariable int id){
		return productDao.listActiveProductsByCategory(id);
	}
}
