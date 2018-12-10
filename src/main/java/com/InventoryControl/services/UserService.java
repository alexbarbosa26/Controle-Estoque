package com.InventoryControl.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.InventoryControl.security.UserSS;

public class UserService {
	
	//Retorna o usuário logado
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
			
		}
	}
	
	

}
