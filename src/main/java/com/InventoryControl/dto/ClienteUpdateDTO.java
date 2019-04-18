package com.InventoryControl.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Cliente;

public class ClienteUpdateDTO implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min = 3, max = 150, message = "Deve conter entre 3 e 150 caracteres")
	private String nome;
	private Integer situacao;
	
	private List<Integer> site_cod = new ArrayList<>();
	
	public ClienteUpdateDTO() {
		
	}
	
	public ClienteUpdateDTO(Cliente dto) {
		
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

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public List<Integer> getSite_cod() {
		return site_cod;
	}

	public void setSite_cod(List<Integer> site_cod) {
		this.site_cod = site_cod;
	}
}
