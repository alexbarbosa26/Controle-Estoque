package com.InventoryControl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	private String nome;
	private Integer quantidade;
	
	@JsonIgnore
	@ManyToMany()
	@JoinTable(name="PRODUTO_CATEGORIA",
			joinColumns=@JoinColumn(name="produto_id"),
			inverseJoinColumns=@JoinColumn(name="categoria_id")
			
			)
	private List<Categoria> categorias = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="codigo.produto")
	private Set<ItemTroca> itens=new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="site_cod")
	private Sites site;
	
	public Produto() {
		
	}

	public Produto(Integer codigo, String nome, Integer quantidade, Sites site) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.quantidade = quantidade;
		this.site = site;
	}
		
	@JsonIgnore
	public List<Trocas> getTrocas(){
		List<Trocas> lista=new ArrayList<>();
		for(ItemTroca x :itens) {
			lista.add(x.getTroca());
		}
		return lista;
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
	

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Set<ItemTroca> getItens() {
		return itens;
	}

	public void setItens(Set<ItemTroca> itens) {
		this.itens = itens;
	}	

	public Sites getSite() {
		return site;
	}

	public void setSite(Sites site) {
		this.site = site;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
}
