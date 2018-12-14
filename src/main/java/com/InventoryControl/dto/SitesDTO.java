package com.InventoryControl.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Sites;

public class SitesDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min =3 , max = 3, message = "Deve conter minimo e máximo 3")
	private String nome;
	
	public SitesDTO() {
		
	}
	
	public SitesDTO(Sites dto) {
		codigo=dto.getCodigo();
		nome = dto.getNome();
	}
	
	public SitesDTO(Integer codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
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
	
	

}
