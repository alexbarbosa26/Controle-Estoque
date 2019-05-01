package com.InventoryControl.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ProdutoUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codigo;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 150, message = "Deve conter entre 5 e 150 caracteres")
	private String nome;
	private Integer quantidade;
	private Integer codSite;

	public ProdutoUpdateDTO() {
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

	public Integer getCodSite() {
		return codSite;
	}

	public void setCodSite(Integer codSite) {
		this.codSite = codSite;
	}
}
