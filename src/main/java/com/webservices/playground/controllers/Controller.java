package com.webservices.playground.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservices.playground.exceptions.UserNotFoundException;
import com.webservices.playground.models.Model;
import com.webservices.playground.models.Post;
import com.webservices.playground.models.User;
import com.webservices.playground.services.PostDaoService;
import com.webservices.playground.services.UserDaoService;

@RestController
public class Controller {
	
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private PostDaoService postDaoService;
	
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
	
	
	//#####################################################################
	//Request methods for USER
	//#####################################################################
	
	@GetMapping(path = "/users")
	public List<User> getAllUser() {
		return userDaoService.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User getUser(@PathVariable int id) {
		User user = userDaoService.findUserById(id);
		if(user == null) {
			//Throw an error and return status code 404
			throw new UserNotFoundException("The user with id : " + id + " is not available.");
		}
		return user;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User newUser = userDaoService.save(user);
		
		/**
		 * As a best practice, 
		 * 1. The API need not return the created resource in the Response Stream. 
		 * 2. It should return an empty response body. 
		 * 3. Instead the API must return a HTTP Status 201 indicating the resource (location URI) was created.
		 */
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	//#####################################################################
	//Request methods for user's POSTS
	//#####################################################################
	
	@GetMapping(path = "/users/{id}/posts")
	public List<Post> getAllPostsOfUser(@PathVariable int id) {
		List<Post> posts = postDaoService.findAllPostsByUserId(id);
		if(posts == null) {
			throw new UserNotFoundException("The user with id : " + id + " is not available.");
		}
		return posts;
	}
	
	@GetMapping(path = "/users/{id}/posts/{postId}")
	public Post getPostByIdOfUser(@PathVariable int id, @PathVariable int postId) throws Exception {
		Post post = postDaoService.findPostbyId(id, postId);
		if(post == null) {
			throw new Exception("The user with id : " + id + " or post with id : "+ postId + " is not available.");
		}
		return post;
	}
	
	@GetMapping(path = "/posts")
	public Map<Integer, ArrayList<Post>> getAllPosts() throws Exception {
		Map<Integer, ArrayList<Post>> posts = postDaoService.findAllPosts();
		return posts;
	}
	
	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Post> addPostOfUser(@PathVariable int id, @RequestBody Post post) throws Exception{
		Post p = postDaoService.addPostByUserId(id, post);
		
		if(post == null) {
			throw new Exception("The user with id : " + id + " is not available or post is null.");
		}
			
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{postId}").buildAndExpand(p.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
