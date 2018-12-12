package com.InventoryControl.services;

import org.springframework.mail.SimpleMailMessage;

import com.InventoryControl.domain.Trocas;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Trocas obj);
	void sendEmail(SimpleMailMessage msg);
	
	

}
