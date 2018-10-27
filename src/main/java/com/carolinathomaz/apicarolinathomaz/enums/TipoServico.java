package com.carolinathomaz.apicarolinathomaz.enums;

//Tipos de serviços disponíveis para contratar 

public enum TipoServico {

	SEDEX(1,"Sedex 10"),
	MALADIRETA(2,"Mala Direta"),
	PAC(3, "PAC");
	
	private int id;
	private String descricao;
	
	private TipoServico(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoServico toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for (TipoServico x : TipoServico.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido");
	}
	
}
