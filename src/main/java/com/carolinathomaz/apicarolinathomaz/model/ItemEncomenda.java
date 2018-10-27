package com.carolinathomaz.apicarolinathomaz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class ItemEncomenda implements Serializable{
    private static final long serialVersionUID = 1L;
	
    @ManyToOne
    private Encomenda encomenda;
    
    @NotNull
	private Integer quantidade;
    
  	@ManyToOne
	@NotNull
	private Embalagem embalagem;
	
	public ItemEncomenda() {
	}

	public Encomenda getEncomenda() {
		return encomenda;
	}

	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Embalagem getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}

}
