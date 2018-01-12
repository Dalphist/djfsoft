package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mapper.manage.UserMapper;
import pojo.User;

import service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	public User getUser(User user) {
		return userMapper.get(user);
	}

	@Override
	public void addUser(User user) {
		userMapper.add(user);
	}
	
	
}
