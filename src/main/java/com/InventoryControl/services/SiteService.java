package com.InventoryControl.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Sites;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.SiteRepository;

@Service
public class SiteService {
	
	@Autowired
	private SiteRepository repo;
	
	//metodo que lista todos os sites ordenados pelo nome
	public List<Sites> findAll(){
		return repo.findAllByOrderByNome();
	}
	
	//Metodo que busca pelo codigo do site
	public Sites buscarId(Integer cod) {
		Sites obj = repo.findOne(cod);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Codigo: "+ cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}
	//Metodo que insere um novo site
	public Sites insert(Sites obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	//metodo que atualiza o nome do site
	public Sites update(Sites obj) {
		buscarId(obj.getCodigo());
		return repo.save(obj);
	}
	//metodo que deleta do site
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir o site");
			
		}
		
		
	}
	
	
	public List<Sites> findSiteCli(Integer codigo){
		
		List<Sites> obj = repo.findSitesByClientes(codigo); 
		
		if(obj == null) {
			throw new ObjectNotFoundException("Codigo: "+ codigo, "não encontrado, favor verificar o codigo digitado!");
		}
		
		return obj;
		
	}

}
