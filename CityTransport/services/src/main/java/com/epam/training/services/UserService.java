package com.epam.training.services;

import java.util.List;

import com.epam.training.dataaccess.model.User;

public interface UserService {

	boolean authenticate(User user);
	
	String getRole(Long id);

	void registrationUser(User user);

	List<User> getAll();

	List<User> getAll(long first, long count);
	
	List<User> getAll(long first, long count,String field,String order);

}
