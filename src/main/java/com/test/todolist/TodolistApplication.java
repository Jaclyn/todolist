package com.test.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.test.todolist.auth.LoginCallbackServlet;
import com.test.todolist.auth.LoginServlet;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}
	
	 // Register Servlet
	  @Bean
	  public ServletRegistrationBean LoginServlet() {
	    ServletRegistrationBean bean = new ServletRegistrationBean(
	        new LoginServlet(), "/login");
	    return bean;
	  }
	  
	  // Register Servlet
	  @Bean
	  public ServletRegistrationBean LoginCallbackServlet() {
	    ServletRegistrationBean bean = new ServletRegistrationBean(
	        new LoginCallbackServlet(), "/login-callback");
	    return bean;
	  }
	  
	  @Bean
	  public RestTemplate restTemplate() {
	      return new RestTemplate();
	  }
	  

}
