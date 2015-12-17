package com.example.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.example.user.service.UserLevelUpgradePolicy;
import com.example.user.service.UserLevelUpgradePolicyImpl;
import com.example.user.service.UserService;

@Configuration
public class DaoFactory {
	@Bean
	public UserService userService() {
		UserService userService = new UserService();
		userService.setUserDao(userDao());
		userService.setUserLevelUpgradePolicy(userLevelUpgradePolicy());
		return userService;
	}
	
	@Bean
	private UserLevelUpgradePolicy userLevelUpgradePolicy() {
		UserLevelUpgradePolicyImpl userLevelUpgradePolicy = new UserLevelUpgradePolicyImpl();
		userLevelUpgradePolicy.setUserDao(userDao());
		return userLevelUpgradePolicy;
	}

	@Bean
	public UserDao userDao() {
		UserDaoJdbc userDao = new UserDaoJdbc();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/test");
		dataSource.setUsername("test");
		dataSource.setPassword("test");
		
		return dataSource;
	}
	
	@Bean
	public JdbcContext jdbcContext() {
		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource());
		return context;
	}
}
