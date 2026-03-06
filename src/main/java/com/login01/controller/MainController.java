package com.login01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/ho")
	public String dasf() {
		
		return "fdsfa";
	}
	
}
