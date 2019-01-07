package com.InventoryControl.services;

import java.util.Arrays;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Categoria;
import com.InventoryControl.domain.Produto;
import com.InventoryControl.dto.ProdutoDTO;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.CategoriaRepository;
import com.InventoryControl.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository repoCategoria;

	//metodo que insere um novo produto via DTO
	public Produto insert(Produto obj) {		

		repoCategoria.save(obj.getCategorias());
		
		return repo.save(obj);
	}
	//metodo DTO que valida as informações lançadas pelo usuario
	public Produto fromDTO(ProdutoDTO dto) {
		
		Produto produtos = new Produto(null, dto.getNome(), dto.getQuantidade());
		
		Categoria categoria = repoCategoria.findOne(dto.getCodCategoria());
		
		categoria.getProdutos().addAll(Arrays.asList(produtos));		
		produtos.getCategorias().addAll(Arrays.asList(categoria));
		
		
		return produtos;
		
	}

	//metodo que faz a busca via codigo do produto
	public Produto buscarId(Integer cod) {

		Produto obj = repo.findOne(cod);
		if (obj == null) {
			throw new ObjectNotFoundException("Codigo: " + cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}

	//metodo que lista todos os produtos
	public List<Produto> findAll() {
		return repo.findAll();
	}

	//metodo que atualiza as informações dos produtos
	public Produto update(Produto obj) {
		buscarId(obj.getCodigo());
		return repo.save(obj);
	}

	//metodo que deteleta os produtos
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o produto");

		}

	}

}
