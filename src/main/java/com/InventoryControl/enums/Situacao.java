package com.InventoryControl.enums;

public enum Situacao {
	
	ATIVO(1,"Ativo"), INATIVO(2,"Inativo");
	
	private int cod;
	private String descricao;
	
	private Situacao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Situacao toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (Situacao x : Situacao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
