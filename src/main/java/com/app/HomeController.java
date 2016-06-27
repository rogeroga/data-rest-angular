package com.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home controller
 * 
 * @author rrobles2
 */
@Controller
public class HomeController {

	@RequestMapping("/home")
	public String home() {
		return "index";
	}
	
}
