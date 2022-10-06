package com.sds.demo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.sds.demo")
@PropertySource({"classpath:db.properties"})
public class DemoConfigApp implements WebMvcConfigurer{
	
     
	@Autowired
	public Environment env;	
   
   private Logger logger=Logger.getLogger(getClass().getName());
	
	@Bean
	public DataSource getdataSource() {
		
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
		
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
				logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
				logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
				
				// set database connection props
				dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
				dataSource.setUser(env.getProperty("jdbc.user"));
				dataSource.setPassword(env.getProperty("jdbc.password"));
				
				// set connection pool props
				dataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
				dataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
				dataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));		
				dataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

				return dataSource;
		
	}
	
	
	private Properties getHibernatesProperties() {
		
		Properties props=new Properties();
		
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return props;	
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(getdataSource());
		sessionFactory.setHibernateProperties(getHibernatesProperties());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
		
	}
	
  private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}	

}
