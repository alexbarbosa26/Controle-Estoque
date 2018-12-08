package com.InventoryControl.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Categoria;
import com.InventoryControl.dto.CategoriaDTO;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarId(Integer cod) {
		Categoria obj = repo.findOne(cod);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Codigo: "+ cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}
	
	//Buscando todos os registros 
	public List<Categoria> findAll(){
		
		return repo.findAll();
		
	}
	
	//Inserindo registro
	public Categoria insert(Categoria obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		
		Categoria categoria = new Categoria(null, dto.getNome());
		
		return categoria;
		
	}
	
	public Categoria update(Categoria obj) {
		
		buscarId(obj.getCodigo());
		return repo.save(obj);
	}
	
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		} catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produto");
		}
	}

}
