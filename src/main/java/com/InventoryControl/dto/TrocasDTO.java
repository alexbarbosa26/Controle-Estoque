package com.InventoryControl.dto;

import java.io.Serializable;
import java.util.Date;

import com.InventoryControl.services.validation.CustomerDateAndTimeDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class TrocasDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	private Date dataTroca;
	
	private Integer codUsuario;
	private Integer quantidadeTroca;
	
	private Integer codProduto;
	
	public TrocasDTO() {
		
	}

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

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Integer getQuantidadeTroca() {
		return quantidadeTroca;
	}

	public void setQuantidadeTroca(Integer quantidadeTroca) {
		this.quantidadeTroca = quantidadeTroca;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}
	
	

}
