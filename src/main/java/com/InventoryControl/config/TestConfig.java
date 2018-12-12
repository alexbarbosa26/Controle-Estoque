package com.InventoryControl.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.InventoryControl.services.DBService;
import com.InventoryControl.services.EmailService;
import com.InventoryControl.services.MockEmailService;
import com.InventoryControl.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService service;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		service.instantiateTestDataBase();
		
		return true;
	}
	/*
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}*/
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
