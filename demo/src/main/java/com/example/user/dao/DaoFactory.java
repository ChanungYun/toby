package com.example.user.dao;

import javax.sql.DataSource;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;

import com.example.user.service.DummyMailSender;
import com.example.user.service.TransactionAdvice;
import com.example.user.service.UserServiceImpl;
import com.example.user.service.UserServiceTest;

@Configuration
public class DaoFactory {
	@Bean
	public ProxyFactoryBean userService() {
		ProxyFactoryBean userService = new ProxyFactoryBean();
		userService.setTarget(userServiceImpl());
		userService.setInterceptorNames("transactionAdvisor");
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
	
	@Bean
	public TransactionAdvice transactionAdvice() {
		TransactionAdvice transactionAdvice = new TransactionAdvice();
		transactionAdvice.setTransactionManager(transactionManager());
		return transactionAdvice;
	}
	
	@Bean
	public AspectJExpressionPointcut transactionPointcut() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* *..*ServiceImpl.upgrade*(..))");
		return pointcut;
	}
	
	@Bean
	public DefaultPointcutAdvisor transactionAdvisor() {
		DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
		defaultPointcutAdvisor.setAdvice(transactionAdvice());
		defaultPointcutAdvisor.setPointcut(transactionPointcut());
		return defaultPointcutAdvisor;
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		return defaultAdvisorAutoProxyCreator;
	}
	
	@Bean
	public UserServiceTest.TestUserServiceImpl testUserService() {
		UserServiceTest.TestUserServiceImpl testUserServiceImpl = new UserServiceTest.TestUserServiceImpl();
		return testUserServiceImpl;
		
	}
}
