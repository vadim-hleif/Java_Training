package com.epam.training.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.epam.training.dataaccess.dao.UserDao;
import com.epam.training.dataaccess.dao.UserTypeDao;
import com.epam.training.dataaccess.model.User;
import com.epam.training.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserTypeDao userTypeDao;

	private String defaultUserType = "user";

	@Override
	public boolean authenticate(User user) {
		try {
			User newUser = userDao.getByLogin(user.getLogin());
			user.setId(newUser.getId());
			user.setUserTypeId(newUser.getUserTypeId());
			return true;
		} catch (DataAccessException e) {
			return false;
		}
	}

	@Override
	public void registrationUser(User user) {
		user.setUserTypeId(userTypeDao.getIdByType(defaultUserType));
		userDao.insert(user);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	public List<User> getAll(long first, long count) {
		return userDao.getAll(first, count);
	}

	public List<User> getAll(long first, long count, String field, String order) {
		return userDao.getAll(first, count);
	}

	@Override
	public String getRole(Long id) {
		return userTypeDao.getById(id).getType();
	}

}
