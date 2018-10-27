package com.carolinathomaz.apicarolinathomaz.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinathomaz.apicarolinathomaz.model.Encomenda;
import com.carolinathomaz.apicarolinathomaz.repositories.EmbalagemRepository;
import com.carolinathomaz.apicarolinathomaz.repositories.EncomendaRepository;

@Service
public class EncomendaService {
	
	@Autowired
	private EncomendaRepository encomendaRepository;
	
	@Autowired
	private EmbalagemRepository embalagemRepository;

	public Encomenda adicionar(Encomenda encomenda) {
		encomenda.setId(null);
		encomenda.setDataPostagem((LocalDateTime.now()));
		encomenda.getItens().forEach(i -> {
			i.setEmbalagem((embalagemRepository.findById(i.getEmbalagem().getId()).get()));
		});
		
		/*
		 * Utilizando stream
                       1 - temos todos os itens da encomenda 
                       2 - para cada item tenho valor da embalagem e a quantidade adquirida
                       3 - usando reduce para retornar um valor
                       4 - BigDecimal.ZERO - valor informado caso não haver dados no stream
                       5 - BigDecimal::add - Operador para executar
		 */
		
		BigDecimal totalItens = encomenda.getItens().stream()
				.map(i -> i.getEmbalagem().getValor().multiply(new BigDecimal(i.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal :: add);
		
		encomenda.setValorTotal(totalItens);
		return encomendaRepository.save(encomenda);
	}
	
}
