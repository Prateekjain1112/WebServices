package com.webservices.playground.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.webservices.playground.models.User;

@Component
public class UserDaoService {
	private static List<User> userList = new ArrayList<>();
	private static int num_Current_Users = 0;
	
	@Autowired
	PostDaoService postDaoService;
	
	public List<User> findAll() {
		return userList;
	}
	
	public User save(User user) {
		num_Current_Users++;
		if(user.getId() == null) {
			user.setId(num_Current_Users);
		}
		postDaoService.addUser(user.getId());
		userList.add(user);
		return user;
	}
	
	public User findUserById(int id) {
		int i = 0;
		if (!userList.isEmpty()) {
			for (i = 0; i < userList.size(); i++) {
				if(userList.get(i).getId() == id) {
					break;
				}
			}
		} else
			return null;
		
		return userList.get(i);
	}
}
