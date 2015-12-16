package com.example.user.dao;

import java.util.List;

import com.example.user.User;

public interface UserDao {
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
}
