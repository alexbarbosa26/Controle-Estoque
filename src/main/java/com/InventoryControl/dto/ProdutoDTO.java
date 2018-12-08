package com.InventoryControl.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	@NotEmpty(message="Preenchimento Obrigátorio")
	@Length(min = 5, max = 150, message = "Deve conter entre 5 e 150 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigátorio")
	@Length(min = 1, message = "Minimo 1 para efetuar uma troca")
	private Integer quantidade;
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto obj) {
		codigo = obj.getCodigo();
		nome = obj.getNome();
		quantidade = obj.getQuantidade();
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

	
	
}
