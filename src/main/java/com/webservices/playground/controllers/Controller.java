package com.webservices.playground.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.playground.models.Model;

@RestController
public class Controller {
	
	@GetMapping("/helloMessage")
	public String getHelloMessage() {
		return "Hello, this is my first Rest Web service";
	}
	
	@GetMapping("/helloMessage/{message}")
	public String getHelloMessageWithPathVariable(@PathVariable String message) {
		return String.format("Hello, this is my first Rest Web service %s", message);
	}
	
	@GetMapping("/helloMessageWithBean")
	public Model getHelloMessageWithBean() {
		return new Model("Hello", 2);
	}
}
