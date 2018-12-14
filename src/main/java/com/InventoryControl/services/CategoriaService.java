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
	
	//metodo para buscar categoria e seus produtos pelo codigo da categoria
	public Categoria buscarId(Integer cod) {
		Categoria obj = repo.findOne(cod);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Codigo: "+ cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}
	
	//metodo que buscando e lista todos os registros 
	public List<Categoria> findAll(){
		
		return repo.findAll();
		
	}
	
	//Metodo para inserir um nova categoria via DTO
	public Categoria insert(Categoria obj) {
		obj.setCodigo(null);
		return repo.save(obj);
	}
	
	//Metodo DTO para validar as informações lançadas pelo usuario
	public Categoria fromDTO(CategoriaDTO dto) {
		
		Categoria categoria = new Categoria(null, dto.getNome());
		
		return categoria;
		
	}
	//metodo para atualizar as informações da categoria
	public Categoria update(Categoria obj) {
		
		buscarId(obj.getCodigo());
		return repo.save(obj);
	}
	
	//metodo para deletar uma categoria pela ID
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		} catch(DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produto");
		}
	}

}
