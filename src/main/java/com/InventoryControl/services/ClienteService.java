package com.InventoryControl.services;

import java.util.Arrays;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Cliente;
import com.InventoryControl.domain.Sites;
import com.InventoryControl.dto.ClienteDTO;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repoCliente;
	
	private SiteService serviceSite;
	
	//metodo para buscar cliente pelo codigo
	public Cliente buscarId(Integer codigo) {
		
		Cliente obj = repoCliente.findOne(codigo);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Codigo: "+ codigo, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}
	
	//metodo que buscando e lista todos os registros
	public List<Cliente> findAll(){
		return repoCliente.findAll();
	}
	
	//Metodo para inserir um novo cliente via DTO
	public Cliente insert(Cliente obj) {
		
		obj.setCodigo(null);
		return repoCliente.save(obj);
		
	}
	
	//Metodo DTO para validar as informações lançadas pelo usuario
	public Cliente fromDTO(ClienteDTO objDTO) {
		
		Sites site = serviceSite.buscarId(objDTO.getSite_cod());
		
		Cliente cliente = new Cliente(null, objDTO.getNome());
		
		site.getClientes().addAll(Arrays.asList(cliente));
		
		return cliente;
	}
	
	//Metodo para atualizar os dados
	public Cliente update(Cliente obj) {
		
		buscarId(obj.getCodigo());
		return repoCliente.save(obj);
	}
	
	//Metodo para excluir o cliente
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repoCliente.delete(cod);
				
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui celula");
		}
		
	}

}
