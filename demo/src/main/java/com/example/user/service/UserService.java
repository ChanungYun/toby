package com.example.user.service;

import java.util.List;

import com.example.user.User;

public interface UserService {
	public void upgradeLevels();
	public void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	void update(User user);
}