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
	
	public List<Sites> findAll(){
		return repo.findAll();
	}
	
	public Sites buscarId(Integer cod) {
		Sites obj = repo.findOne(cod);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Codigo: "+ cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}
	
	public Sites insert(Sites obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Sites update(Sites obj) {
		buscarId(obj.getCodigo());
		return repo.save(obj);
	}
	
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		}catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir o site");
			
		}
		
		
	}

}
