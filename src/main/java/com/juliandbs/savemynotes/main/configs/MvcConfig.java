package com.juliandbs.savemynotes.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home/home");
		registry.addViewController("/home").setViewName("home/home");
		registry.addViewController("/desktop").setViewName("desktop/desktop");
		registry.addViewController("/login").setViewName("login/login");
		registry.addViewController("/registration").setViewName("registration/registration");
	}
}
