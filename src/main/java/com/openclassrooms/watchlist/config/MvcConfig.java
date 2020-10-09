package com.openclassrooms.watchlist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Per default with Spring boot mvc, the home page is index.html
 * If you want to change it for home.html for example, create a class with @Configuration annotation as follows
 * and rename index.html by home.html
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{

	@Override 
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("home.html");
	    registry.addViewController("/home").setViewName("home.html");
	}
}