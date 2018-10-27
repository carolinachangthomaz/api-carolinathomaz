package com.carolinathomaz.apicarolinathomaz.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class ItemEncomenda implements Serializable{
    private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    
    @ManyToOne
    private Encomenda encomenda;
    
    @NotNull
	private Integer quantidade;
    
  	@ManyToOne
	@NotNull
	private Embalagem embalagem;
	
	public ItemEncomenda() {
	}

	public ItemEncomenda(Long id, Encomenda encomenda, @NotNull Integer quantidade, @NotNull Embalagem embalagem) {
		super();
		this.id = id;
		this.encomenda = encomenda;
		this.quantidade = quantidade;
		this.embalagem = embalagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ItemEncomenda other = (ItemEncomenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
