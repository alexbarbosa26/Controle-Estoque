package com.InventoryControl.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.InventoryControl.domain.Categoria;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String nome;
	private Integer quantidade;
	private List<Categoria> categoriasCod;
	
	public ProdutoDTO() {	
		
		this.categoriasCod = new ArrayList<>();
	}

	

	public ProdutoDTO(Integer codigo, String nome, Integer quantidade, List<Categoria> categoriasCod) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.quantidade = quantidade;
		this.categoriasCod = categoriasCod;
	}



	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<Categoria> getCategoriasCod() {
		return categoriasCod;
	}

	public void setCategoriasCod(List<Categoria> categoriasCod) {
		this.categoriasCod = categoriasCod;
	}
	
}
