package com.carolinathomaz.apicarolinathomaz.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.carolinathomaz.apicarolinathomaz.enums.StatusEncomenda;

//Atualização de status - utilizado para rastrear a encomenda
/*
 *                 POSTADO - cliente posta a encomenda com itens
 *                 EMTRANSITO - encomenda com destino centro de distribuição x
 *                 ENTRADA - a encomenda chega no centro de distribuição x
 *                 EMTRANSITOPARACLIENTE - encomenda com destino informado pelo cliente
 *                 ENTREGUE - encomenda entregue para o cliente é orbigatório upload de comprovante de entrega
 */
@Entity
public class StatusAlteracao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date data;
	private StatusEncomenda statusEncomenda;
	
	@ManyToOne
	@JoinColumn(name="encomenda_id")
	private Encomenda encomenda;
	
	public StatusAlteracao() {
	}

	public StatusAlteracao(Integer id, Date data, StatusEncomenda statusEncomenda, Encomenda encomenda) {
		super();
		this.id = id;
		this.data = data;
		this.statusEncomenda = statusEncomenda;
		this.encomenda = encomenda;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt" , "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Encomenda: ");
		builder.append(getEncomenda().getId());
		builder.append("\n");
		builder.append("Status: ");
		builder.append(getStatusEncomenda());
		builder.append("\n");
		builder.append("Data: ");
		builder.append(sdf.format(getData()));
		builder.append("\n");
		builder.append("Cliente:");
		builder.append(getEncomenda().getCliente().getNome());
		builder.append("\n");
		builder.append("Valor Pago:");
		builder.append(nf.format(getEncomenda().getValorTotal()));
		builder.append("\n");
		builder.append("\n");
		builder.append("Destino: ");
		builder.append(getEncomenda().getLocalDestinoCliente());
		return builder.toString();
	}

	
	

}
