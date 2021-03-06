package com.InventoryControl.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemTroca implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemTrocaPK codigo = new ItemTrocaPK();

	private Integer quantidadeTroca;
	private String numeroChamado;
	private String motivo;

	public ItemTroca() {

	}

	public ItemTroca(Trocas troca, Produto produto, Integer quantidadeTroca, String numeroChamado, String motivo) {
		super();
		codigo.setTroca(troca);
		codigo.setProduto(produto);
		this.quantidadeTroca = quantidadeTroca;
		this.numeroChamado = numeroChamado;
		this.motivo = motivo;
	}
	/*
	 * public Integer getSobra() { if(quantidadeTroca != 0 &&
	 * codigo.getProduto().getQuantidade() != 0) {
	 * if(codigo.getProduto().getQuantidade() < quantidadeTroca) { return 0; }
	 * return (codigo.getProduto().getQuantidade()-quantidadeTroca); } return
	 * getSobra(); }
	 */

	@JsonIgnore
	public Trocas getTroca() {
		return codigo.getTroca();
	}

	public void setTroca(Trocas troca) {
		codigo.setTroca(troca);
	}

	public Produto getProduto() {
		return codigo.getProduto();
	}

	public void setProduto(Produto produto) {
		codigo.setProduto(produto);

	}

	public ItemTrocaPK getCodigo() {
		return codigo;
	}

	public void setCodigo(ItemTrocaPK codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidadeTroca() {
		return quantidadeTroca;
	}

	public void setQuantidadeTroca(Integer quantidadeTroca) {
		this.quantidadeTroca = quantidadeTroca;
	}	

	public String getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(String numeroChamado) {
		this.numeroChamado = numeroChamado;
	}	

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
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
		ItemTroca other = (ItemTroca) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Qtd: ");
		builder.append(getProduto().getQuantidade());
		builder.append(", Qtd Estoque: ");
		builder.append(quantidadeTroca);
		builder.append("\n");
		return builder.toString();
	}
	
	

}
