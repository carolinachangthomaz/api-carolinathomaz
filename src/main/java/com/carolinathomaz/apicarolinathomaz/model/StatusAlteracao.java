package com.carolinathomaz.apicarolinathomaz.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.carolinathomaz.apicarolinathomaz.enums.StatusEncomenda;

@Entity
public class StatusAlteracao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime data;
	private StatusEncomenda statusEncomenda;
	
	@ManyToOne
	private Encomenda encomenda;
	
	public StatusAlteracao() {
		// TODO Auto-generated constructor stub
	}

	public StatusAlteracao(Integer id, StatusEncomenda statusEncomenda, Encomenda encomenda) {
		super();
		this.id = id;
		this.statusEncomenda = statusEncomenda;
		this.encomenda = encomenda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public StatusEncomenda getStatusEncomenda() {
		return statusEncomenda;
	}

	public void setStatusEncomenda(StatusEncomenda statusEncomenda) {
		this.statusEncomenda = statusEncomenda;
	}

	public Encomenda getEncomenda() {
		return encomenda;
	}

	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
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
		StatusAlteracao other = (StatusAlteracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
