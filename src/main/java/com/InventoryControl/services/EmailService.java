package com.InventoryControl.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.InventoryControl.domain.Trocas;
import com.InventoryControl.domain.Usuario;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Trocas obj);
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Trocas obj);
	void sendHtmlEmail(MimeMessage msg);
	void sendNewPasswordEmail(Usuario usuario, String newPsw);
	
	

}
