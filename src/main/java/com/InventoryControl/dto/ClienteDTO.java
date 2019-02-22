package com.InventoryControl.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min = 5, max = 150, message = "Deve conter entre 5 e 150 caracteres")
	private String nome;
	
	private Integer site_cod;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente dto) {
		
		codigo=dto.getCodigo();
		nome=dto.getNome();
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

	public Integer getSite_cod() {
		return site_cod;
	}

	public void setSite_cod(Integer site_cod) {
		this.site_cod = site_cod;
	}	
	
}
