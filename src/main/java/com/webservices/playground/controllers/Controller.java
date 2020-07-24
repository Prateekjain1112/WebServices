package com.webservices.playground.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.playground.models.Model;
import com.webservices.playground.models.User;
import com.webservices.playground.services.UserDaoService;

@RestController
public class Controller {
	
	@Autowired
	private UserDaoService userDaoService;
	
	//A simple end point
	@GetMapping(path = "/helloMessage")
	public String getHelloMessage() {
		return "Hello, this is my first Rest Web service";
	}
	
	//Using a path variable
	@GetMapping(path = "/helloMessage/{message}")
	public String getHelloMessageWithPathVariable(@PathVariable String message) {
		return String.format("Hello, this is my first Rest Web service %s", message);
	}
	
	//return a bean/model defined with GETTER methods
	@GetMapping(path = "/helloMessageWithBean")
	public Model getHelloMessageWithBean() {
		return new Model("Hello", 2);
	}
	
	@PostMapping(path = "/users")
	public User addUser(@RequestBody User user) {
		return userDaoService.save(user);
	}
	
	@GetMapping(path = "/users")
	public List<User> getAllUser() {
		return userDaoService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User getUser(@PathVariable int id) {
		return userDaoService.findUserById(id);
	}
}
