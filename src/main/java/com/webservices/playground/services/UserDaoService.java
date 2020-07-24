package com.webservices.playground.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.webservices.playground.models.User;

@Component
public class UserDaoService {
	private static List<User> userList = new ArrayList<>();
	private static int num_Current_Users = 0;
	
	public List<User> findAll() {
		return userList;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			num_Current_Users++;
			user.setId(num_Current_Users);
		}
		
		userList.add(user);
		return user;
	}
	
	public User findUserById(int id) {
		int i = 0;
		for (i = 0; i < userList.size(); i++) {
			if(userList.get(i).getId() == id) {
				break;
			}
		}
		return userList.get(i);
	}
}
