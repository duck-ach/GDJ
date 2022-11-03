package com.gdu.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.contact.repository.ContactDao;
import com.gdu.contact.service.ContactService;
import com.gdu.contact.service.ContactServiceImpl;

@Configuration
public class SpringConfig {

	@Bean
	public ContactService contactService() {
		return new ContactServiceImpl();
	}
	
	@Bean
	public ContactDao contactDao() {
		return new ContactDao();
	}
}
