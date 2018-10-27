package com.carolinathomaz.apicarolinathomaz.enums;

//Status da encomenda - Acompanhando do início até a entrega para  cliente

public enum StatusEncomenda {

	POSTADO(1,"Postado"),
	EMTRANSITO(2,"Em Transito"),
	EMTRANSITOPARACLIENTE(3, "Em Transito para o Cliente"),
	ENTRADA(4, "Entrada"),
	ENTREGUE(5, "Entregue");
	
	private int id;
	private String descricao;
	
	private StatusEncomenda(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusEncomenda toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		
		for (StatusEncomenda x : StatusEncomenda.values()) {
			if(id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido");
	}
	
}
