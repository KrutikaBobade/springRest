package com.clc.spring.restapi;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableWebMvc
@Configuration
@ComponentScan
@EnableSwagger2
//@PropertySource("${classpath:database.properties}") // spring-hibernate
@PropertySource("classpath:database.properties")//spring mvc

public class HibernateSpringConfig {

	@Value("${spring.hibernate.hsqldb.username}")
	String SQL_USERNAME;
	
	@Value("${spring.hibernate.hsqldb.password}")
	String SQL_PASSWORD;
	
	@Value("${spring.hibernate.hsqldb.driver}")
	String SQL_DRIVER;
	
	@Value("${spring.hibernate.hsqldb.url}")
	String SQL_URL;
	
	@Value("${spring.hibernate.hsqldb.dialect}")
	String SQL_DIALECT;
	
	@Value("${spring.hiernate.create.tables}")
	String SQL_CREATE_TABLE;
	
	@Value("${spring.hiernate.show.sql}")
	String SQL_SHOW;
	
	@Value("${spring.hiernate.format.sql}")
	String SQL_FORMAT;
	
	@Value("${spring.hiernate.scan.pckg}")
	String SQL_SCAN_PACKAGE;
	
	
	static {
		System.out.println("Spring configuration loaded");
	}
	

	public BasicDataSource createDbConfiguration() {
		BasicDataSource basicDb = new BasicDataSource();
		basicDb.setUrl(SQL_URL);
		basicDb.setUsername(SQL_USERNAME);
		basicDb.setPassword(SQL_PASSWORD);
		basicDb.setDriverClassName(SQL_DRIVER);
		return basicDb;
	}


	public LocalSessionFactoryBean createSessionfactory() {
		System.out.println("Localsessionfactory instance created...!");
		LocalSessionFactoryBean localBean = new LocalSessionFactoryBean();
		localBean.setDataSource(createDbConfiguration());
		localBean.setPackagesToScan(SQL_SCAN_PACKAGE);
		localBean.setHibernateProperties(getHibernateProperties());
		return localBean;
	}
	
	public Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put(Environment.DIALECT,SQL_DIALECT);
		prop.put(Environment.SHOW_SQL,true);
		prop.put("hbm2ddl.auto","update");
		//prop.put(Environment.HBM2DDL_AUTO,SQL_CREATE_TABLE);
		prop.put("hibernate.temp.use_jdbc_metadata_defaults",false);
		return prop;
	}
	
	
	// Describe your apis
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Sample APIs")
                .description("This page lists all the rest apis for Swagger Sample App.")
                .version("1.0-SNAPSHOT")
                .build();
    }

    // Only select apis that matches the given Predicates.
    private Predicate<String> paths() {
    	// Match all paths except /error
        return Predicates.and(
        	PathSelectors.regex("/.*"), 
        	Predicates.not(PathSelectors.regex("/error.*"))
        );
    }
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build();
    }	
	
	
}
