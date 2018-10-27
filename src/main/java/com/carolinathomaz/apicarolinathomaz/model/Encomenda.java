package com.carolinathomaz.apicarolinathomaz.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.carolinathomaz.apicarolinathomaz.enums.StatusEncomenda;
import com.carolinathomaz.apicarolinathomaz.enums.TipoServico;
import com.fasterxml.jackson.annotation.JsonIgnore;

// Disponibilizar tipos de serviço 
@Entity
public class Encomenda implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime dataPostagem;
	private TipoServico tipoServico;
	private StatusEncomenda statusEncomenda;
	
	@ManyToOne
	private Cliente cliente;
	
	@JsonIgnore
	@Valid
	@NotEmpty
	@OneToMany(mappedBy = "encomenda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemEncomenda> itens;
	
	private BigDecimal valorTotal;
	
	public Encomenda() {
	}

	public Encomenda(Integer id, LocalDateTime dataPostagem, TipoServico tipoServico, StatusEncomenda estatusEncomenda,
			Cliente cliente, @Valid @NotEmpty List<ItemEncomenda> itens, BigDecimal valorTotal) {
		super();
		addStatusEncomenda(StatusEncomenda.POSTADO);
		this.id = id;
		this.dataPostagem = dataPostagem;
		this.tipoServico = tipoServico;
		this.statusEncomenda = estatusEncomenda;
		this.cliente = cliente;
		this.itens = itens;
		this.valorTotal = valorTotal;
	}

	private void addStatusEncomenda(StatusEncomenda postado) {
		this.statusEncomenda = postado;		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(LocalDateTime dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public StatusEncomenda getStatusEncomenda() {
		return statusEncomenda;
	}

	public void setStatusEncomenda(StatusEncomenda estatusEncomenda) {
		this.statusEncomenda = estatusEncomenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemEncomenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemEncomenda> itens) {
		this.itens = itens;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
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
		Encomenda other = (Encomenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
