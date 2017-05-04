package com.yjy.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yjy.dao.UserDao;
import com.yjy.entity.User;
import com.yjy.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public User getByUserName(String userName) {
		return userDao.getByUserName(userName);
	}

	public Set<String> getRoles(String userName) {
		return userDao.getRoles(userName);
	}

	public Set<String> getPermissions(String userName) {
		return userDao.getPermissions(userName);
	}

	public User login(User loginUser) {
		User user = userDao.getByUserName(loginUser.getUserName());
		if(user.getPassword().equals(loginUser.getPassword())){
			return user;
		}
		return null;
	}

}
