package net.sam.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.sam.onlineshopping.exception.ProductNotFoundException;
import net.sam.shoppingbackend.dao.CategoryDao;
import net.sam.shoppingbackend.dao.ProductDAO;
import net.sam.shoppingbackend.dto.Category;
import net.sam.shoppingbackend.dto.Product;

@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDAO productDao;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");

		logger.info("**********Inside Page Controller Index - INFO");
		logger.debug("*********Inside Page Controller Index - DEBUG");
		// Passing List of categories
		mv.addObject("categories", categoryDao.list());

		mv.addObject("userClickedHome", true);
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickedAbout", true);
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickedContact", true);
		return mv;
	}

	// Method to show all Product based on category

	@RequestMapping(value = { "/show/all/products" })
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// Passing List of categories
		mv.addObject("categories", categoryDao.list());

		mv.addObject("userClickAllProducts", true);
		return mv;
	}

	@RequestMapping(value = { "/show/category/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");

		// categoryDao to fetch a single Category
		Category category = null;
		category = categoryDao.get(id);

		mv.addObject("title", category.getName());

		// Passing List of categories
		mv.addObject("categories", categoryDao.list());

		// Passing Single Category
		mv.addObject("category", category);

		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	//Method to show Single Product
	@RequestMapping(value = { "/show/{id}/product" })
	public ModelAndView showAllSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDao.get(id);
		
		if(product == null) {
			throw new ProductNotFoundException();
		}
		//Update the View Count
		product.setViews(product.getViews()+1);
		productDao.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		
		return mv;
	}

	/*@RequestMapping(value = "/{greeting}")
	public ModelAndView testPathVariable(@PathVariable String greeting) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}*/
}
