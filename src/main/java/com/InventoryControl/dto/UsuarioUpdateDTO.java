package com.InventoryControl.dto;

import java.io.Serializable;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Usuario;
import com.InventoryControl.services.validation.UsuarioUpdate;

@UsuarioUpdate
public class UsuarioUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@Column(unique=true)
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public UsuarioUpdateDTO() {
		
	}
	
	public UsuarioUpdateDTO(Usuario dto) {
		codigo = dto.getCodigo();
		nome = dto.getNome();
		email = dto.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
