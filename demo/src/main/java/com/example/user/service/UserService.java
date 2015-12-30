package com.example.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.user.User;

@Transactional
public interface UserService {
	public void upgradeLevels();
	public void add(User user);
	
	@Transactional(readOnly=true)
	User get(String id);
	@Transactional(readOnly=true)
	List<User> getAll();
	
	void deleteAll();
	void update(User user);
}