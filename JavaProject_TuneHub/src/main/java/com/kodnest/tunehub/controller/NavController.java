package com.kodnest.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	@GetMapping("/registration")
	public String	registration(){
		return "signup";
	}
	@GetMapping("/newsong")
	public String newsong() {
		return "newsong";
	}
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}

