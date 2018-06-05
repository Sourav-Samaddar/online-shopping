package net.sam.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sam.onlineshopping.util.FileUploadUtility;
import net.sam.onlineshopping.validator.ProductValidator;
import net.sam.shoppingbackend.dao.CategoryDao;
import net.sam.shoppingbackend.dao.ProductDAO;
import net.sam.shoppingbackend.dto.Category;
import net.sam.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDAO productDao;
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name="operation",required=false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts",true);
		
		Product nProduct =new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product",nProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully!");
			}
			else if(operation.equals("category")) {
				mv.addObject("message","Category Added Successfully!");
			}
		}
		return mv;
	}
	
	// Handle Category Submission
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		// Add category
		categoryDao.add(category);
		return "redirect:/manage/products?operation=category";
	}
	
	@ModelAttribute("categories")
	public List<Category> getAllCategories(){
		return categoryDao.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
	
	// Show the product for Edit
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Manage Products");
		mv.addObject("userClickManageProducts",true);
		Product product = productDao.get(id);
		mv.addObject("product",product);
		return mv;
	}
	
	//Handling Product Activation
	@RequestMapping(value="/products/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		Product product = productDao.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productDao.update(product);
		
		return (isActive)?"You have successfully deactivated the Product with id: "+id:
			"You have successfully activated the Product with id: "+id;
	}
	
	//Handling Product Submission
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,
			BindingResult results,Model model,HttpServletRequest request){
		
		// Check Spring Validation for image upload
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		// Check if there are validation errors
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation Failed for Product Submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		if(mProduct.getId() == 0) {
			// For adding Product
			productDao.add(mProduct);
		}
		else {
			// For Updating Product
			productDao.update(mProduct);
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.upLoadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
}
