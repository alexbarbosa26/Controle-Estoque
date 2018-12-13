package com.InventoryControl.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.InventoryControl.domain.Trocas;
import com.InventoryControl.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

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

	protected String htmlFromTemplatePedido(Trocas obj) {
		Context context = new Context();

		context.setVariable("trocas", obj);

		return templateEngine.process("email/confirmacaoTroca", context);

	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Trocas obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromTrocas(obj);

			sendHtmlEmail(mm);

		} catch (MessagingException m) {
			sendOrderConfirmationEmail(obj);
		}
	}
	

	protected MimeMessage prepareMimeMessageFromTrocas(Trocas obj) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);

		mmh.setTo(obj.getUsuario().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Baixa de estoque de código: " + obj.getCodigo());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);

		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPsw) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPsw);
		sendEmail(sm);		
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPsw) {
		SimpleMailMessage sm = new SimpleMailMessage();

		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova Senha: " + newPsw);

		return sm;
	}
	
	
}
