package com.InventoryControl.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Cliente;
import com.InventoryControl.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repoCliente;
	
	//metodo para buscar cliente e suas celulas pelo codigo do cliente
	public Cliente bucarId(Integer codigo) {
		
		Cliente obj = repoCliente.findOne(codigo);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Codigo: "+ codigo, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}

}
