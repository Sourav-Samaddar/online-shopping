package net.sam.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView NoHandlerFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","The page is not constructed");
		mv.addObject("errorDescription","The page you are looking for is not available now");
		mv.addObject("title","404 Error page");
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView HandlerProductNotFoundException() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","Product Not Available");
		mv.addObject("errorDescription","The product you are looking for is not available right now");
		mv.addObject("title","Product Unavailable");
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView HandlerException(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle","Contact your system administartor");
		
		// For debugging only
		/*StringWriter sw =new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		mv.addObject("errorDescription",sw.toString());*/
		
		mv.addObject("errorDescription",ex.toString());
		mv.addObject("title","Error");
		return mv;
	}
}
