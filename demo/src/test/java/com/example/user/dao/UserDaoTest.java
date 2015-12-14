package com.example.user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.example.user.User;

public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {		
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user1 = dao.get("whiteship");
		System.out.println(user1.getName());
		System.out.println(user1.getPassword());
		
	}
}
