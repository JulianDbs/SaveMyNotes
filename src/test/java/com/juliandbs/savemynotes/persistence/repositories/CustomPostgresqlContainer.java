package com.juliandbs.savemynotes.persistence.repositories;

import org.testcontainers.containers.PostgreSQLContainer;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.test.context.ActiveProfiles;


public class CustomPostgresqlContainer extends PostgreSQLContainer<CustomPostgresqlContainer> {
	private static final String IMAGE_VERSION = "postgres:11.1";
	private static CustomPostgresqlContainer container;

	@Value("${spring.datasource.url}")
	private static String DB_URL;

	@Value("${spring.datasource.username}")
	private static String DB_USERNAME;

	@Value("${spring.datasource.password}")
	private static String DB_PASSWORD;

	private CustomPostgresqlContainer() {
		super(IMAGE_VERSION);
	}

	public static CustomPostgresqlContainer getInstance() {
		if (container == null)	{
			container = new CustomPostgresqlContainer();
		}
		return container;
	}

	@Override
	public void start() {
		super.start();
		System.setProperty(DB_URL, container.getJdbcUrl());
		System.setProperty(DB_USERNAME, container.getUsername());
		System.setProperty(DB_PASSWORD, container.getPassword());
	}

	@Override
	public void stop() {}

}
