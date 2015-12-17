package com.example.user.dao;

import java.util.List;

import com.example.user.User;

public interface UserDao {
	public int add(User user);
	public User get(String id);
	public List<User> getAll();
	public int deleteAll();
	public int getCount();
	public int update(User user);
}
