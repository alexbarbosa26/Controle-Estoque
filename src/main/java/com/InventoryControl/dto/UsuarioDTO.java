package com.InventoryControl.dto;

import java.io.Serializable;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.InventoryControl.domain.Usuario;
import com.InventoryControl.services.validation.UsuarioInsert;
import com.fasterxml.jackson.annotation.JsonIgnore;


@UsuarioInsert
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@Column(unique=true)
	private String matricula;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@Column(unique=true)
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	@JsonIgnore
	private String senha;
	@JsonIgnore
	private Integer codSite;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario dto) {
		codigo = dto.getCodigo();
		matricula = dto.getMatricula();
		nome = dto.getNome();
		email = dto.getEmail();
		senha = dto.getSenha();
		
	}
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getCodSite() {
		return codSite;
	}

	public void setCodSite(Integer codSite) {
		this.codSite = codSite;
	}

}
