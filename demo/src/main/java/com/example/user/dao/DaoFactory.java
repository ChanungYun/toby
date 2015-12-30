package com.example.user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;

import com.example.user.service.DummyMailSender;
import com.example.user.service.TxProxyFactoryBean;
import com.example.user.service.UserService;
import com.example.user.service.UserServiceImpl;

@Configuration
public class DaoFactory {
	@Bean
	public TxProxyFactoryBean userService() {
		TxProxyFactoryBean userService = new TxProxyFactoryBean();
		userService.setTarget(userServiceImpl());
		userService.setTransactionManager(transactionManager());
		userService.setPattern("upgradeLevels");
		userService.setServiceInterface(UserService.class);
		return userService;
	}
	
	@Bean
	public UserServiceImpl userServiceImpl() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setMailSender(mailSender());
		userServiceImpl.setUserDao(userDao());
		return userServiceImpl;
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
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
	
	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}
}
