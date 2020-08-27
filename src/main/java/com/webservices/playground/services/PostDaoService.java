package com.webservices.playground.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.webservices.playground.models.Post;

@Component
public class PostDaoService {
	
	private static Map<Integer, ArrayList<Post>> map = new HashMap<>();
	
	public void addUser(Integer id) {
		map.put(id, new ArrayList<Post>());
	}
	
	//To find all posts of all users
	public Map<Integer, ArrayList<Post>> findAllPosts() {
		return map;
	}
	
	//To find all posts of by user ID
	public List<Post> findAllPostsByUserId(Integer userId) {
		if(map.containsKey(userId)) {
				return map.get(userId);
		}
		return null;
	}
	
	//To find post by post ID of a single user by ID
	public Post findPostbyId(Integer userId, Integer postId) {
		if(map.containsKey(userId)) {
			ArrayList<Post> posts = map.get(userId);
			for (int i = 0; i < posts.size(); i++) {
				if(posts.get(i).getId() == postId)
					return posts.get(i);
			}
		}
		return null;
	}
	
	//To add post for a single user
	public Post addPostByUserId(Integer userId, Post post) {
		if(post == null) {
			return null;
		}
		
		if(map.containsKey(userId)) {
			if(post.getId() == null) {
				post.setId(map.get(userId).size()+1);
			}
			map.get(userId).add(post);
		} else
			return null;
		
		return post;
	}
}
