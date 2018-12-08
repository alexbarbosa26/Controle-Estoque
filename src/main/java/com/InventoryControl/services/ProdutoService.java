package com.InventoryControl.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Produto;
import com.InventoryControl.dto.ProdutoDTO;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public Produto insert(Produto obj) {

		return repo.save(obj);

	}
	
	public Produto fromDTO(ProdutoDTO dto) {
		
		Produto produto = new Produto(null, dto.getNome(), dto.getQuantidade());
		
		return produto;
		
	}

	public Produto buscarId(Integer cod) {

		Produto obj = repo.findOne(cod);
		if (obj == null) {
			throw new ObjectNotFoundException("Codigo: " + cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}

	public List<Produto> findAll() {
		return repo.findAll();
	}

	public Produto update(Produto obj) {
		buscarId(obj.getCodigo());
		return repo.save(obj);
	}

	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o produto");

		}

	}

}
