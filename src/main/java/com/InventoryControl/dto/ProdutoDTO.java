package com.InventoryControl.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min = 5, max = 150, message = "Deve conter entre 5 e 150 caracteres")
	private String nome;
	
	//@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Somente números")
	private Integer quantidade;
	
	private String imagem;
	
	private Integer codCategoria;
	
	private Integer codSite;
	
	public ProdutoDTO() {
	}
	
	public ProdutoDTO(Produto obj) {
		codigo = obj.getCodigo();
		nome = obj.getNome();
		quantidade = obj.getQuantidade();
		imagem = obj.getImagem();
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Integer getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Integer codCategoria) {
		this.codCategoria = codCategoria;
	}

	public Integer getCodSite() {
		return codSite;
	}

	public void setCodSite(Integer codSite) {
		this.codSite = codSite;
	}
	
	
	
}
