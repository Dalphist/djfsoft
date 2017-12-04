package service.impl;

import mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.User;

import service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;

	public User getUser(User user) {
		return userMapper.get(user);
	}
	
}
