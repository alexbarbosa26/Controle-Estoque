package com.InventoryControl.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.InventoryControl.domain.Trocas;

public abstract class AbstractEmailService implements EmailService{

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Trocas obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromTrocas(obj);
		
		sendEmail(sm);
	}

	private SimpleMailMessage prepareSimpleMailMessageFromTrocas(Trocas obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		
		sm.setTo(obj.getUsuario().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Baixa de estoque de codigo: " + obj.getCodigo());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}


}
