package com.InventoryControl.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Celula;

public class CelulaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Length(min = 5, max = 150, message = "Deve conter entre 5 e 150 caracteres")
	private String nome;
	
	private String pep;
	
	private Integer cliente_cod;
	
	public CelulaDTO() {
		
	}
	
	public CelulaDTO(Celula dto) {
		codigo=dto.getCodigo();
		nome=dto.getNome();	
		pep=dto.getPep();
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

	public String getPep() {
		return pep;
	}

	public void setPep(String pep) {
		this.pep = pep;
	}

	public Integer getCliente_cod() {
		return cliente_cod;
	}

	public void setCliente_cod(Integer cliente_cod) {
		this.cliente_cod = cliente_cod;
	}

}
