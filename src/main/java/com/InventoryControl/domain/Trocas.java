package com.InventoryControl.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Trocas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataTroca;
	
	@ManyToOne
	@JoinColumn(name="usuario_cod")
	private Usuario usuario;
	
	@OneToMany(mappedBy="codigo.troca")
	private Set<ItemTroca> itens = new HashSet<>();
	
	public Trocas() {
		
	}

	public Trocas(Integer codigo, Date dataTroca, Usuario usuario) {
		super();
		this.codigo = codigo;
		this.dataTroca = dataTroca;
		this.usuario = usuario;
	}
	
	/*
	public Integer getValorTotal() {
		int valor = 0;
		for(ItemTroca i: itens) {
			
			valor = valor + i.getSaida();
		}
		return valor;
	}*/

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getDataTroca() {
		return dataTroca;
	}

	public void setDataTroca(Date dataTroca) {
		this.dataTroca = dataTroca;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<ItemTroca> getItens() {
		return itens;
	}

	public void setItens(Set<ItemTroca> itens) {
		this.itens = itens;
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
		Trocas other = (Trocas) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		
		StringBuilder builder = new StringBuilder();
		builder.append("codigo troca: ");
		builder.append(codigo);
		builder.append(", Data Troca: ");
		builder.append(date.format(getDataTroca()));
		builder.append(", Matricula: ");
		builder.append(getUsuario().getMatricula());
		builder.append(", Usuario: ");
		builder.append(getUsuario().getNome());
		builder.append("\nItens da Troca: \n");
		
		for(ItemTroca it : getItens()) {
			builder.append(it.toString());
		}
				
		return builder.toString();
	}
	
	

	
}
