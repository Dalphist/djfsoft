package service.manage;

import pojo.User;

public interface UserService {
	User getUser(User user);
	void addUser(User user);
}
