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
		registry.addViewController("/new-note").setViewName("note/new-note");
		//delete this after
		registry.addViewController("/note-not-found").setViewName("note/note-not-found");
		registry.addViewController("/error-401").setViewName("errors/error-401");
		registry.addViewController("/error-403").setViewName("errors/error-403");
		registry.addViewController("/error-404").setViewName("errors/error-404");
		registry.addViewController("/error-500").setViewName("errors/error-500");
		//and delete this ??
		registry.addViewController("/edit-note-success").setViewName("note/edit-note-success");
		registry.addViewController("/edit-note-failure").setViewName("note/edit-note-failure");
	}
}
