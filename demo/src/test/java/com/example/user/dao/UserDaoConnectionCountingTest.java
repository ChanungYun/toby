package com.example.user.dao;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.user.User;

public class UserDaoConnectionCountingTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
		User user = dao.get("whiteship");
		dao.get("whiteship");
		dao.get("whiteship");
		dao.get("whiteship");
		System.out.println(user.getName());
		
		
		System.out.println("Connection counter : " + ccm.getCounter());
	}
}
