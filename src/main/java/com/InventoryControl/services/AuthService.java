package com.InventoryControl.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Usuario;
import com.InventoryControl.repositories.UsuarioRepository;
import com.InventoryControl.resources.exception.ObjectNotFoundException;


@Service
public class AuthService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pswEnconder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		String newPsw = newPassword();
		
		usuario.setSenha(pswEnconder.encode(newPsw));
		
		usuarioRepository.save(usuario);
		emailService.sendNewPasswordEmail(usuario, newPsw);
		
	}

	private String newPassword() {
		char[] vet = new char[20];
		
		for(int i=0; i<20; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(4);
		if(opt ==0) {//Gera um digito
			return (char) (rand.nextInt(10) + 48);
			
		}else if(opt ==1) {//Gera letra maiuscula
			return (char) (rand.nextInt(26)+65);
			
		}else {//Gera letra minuscula
			return (char) (rand.nextInt(26)+97);
		}
	}

}
