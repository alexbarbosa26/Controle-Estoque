package com.InventoryControl.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemTrocaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="troca_cod")
	private Trocas troca;
	
	@ManyToOne
	@JoinColumn(name="produto_cod")
	private Produto produto;

	public Trocas getTroca() {
		return troca;
	}

	public void setTroca(Trocas troca) {
		this.troca = troca;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((troca == null) ? 0 : troca.hashCode());
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
		ItemTrocaPK other = (ItemTrocaPK) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (troca == null) {
			if (other.troca != null)
				return false;
		} else if (!troca.equals(other.troca))
			return false;
		return true;
	}
	
	

}
