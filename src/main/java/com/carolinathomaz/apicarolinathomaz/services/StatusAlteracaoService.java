package com.carolinathomaz.apicarolinathomaz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carolinathomaz.apicarolinathomaz.exception.ObjectNotFoundException;
import com.carolinathomaz.apicarolinathomaz.model.Encomenda;
import com.carolinathomaz.apicarolinathomaz.model.StatusAlteracao;
import com.carolinathomaz.apicarolinathomaz.repositories.EncomendaRepository;
import com.carolinathomaz.apicarolinathomaz.repositories.StatusAlteracaoRepository;

@Service
public class StatusAlteracaoService {

	@Autowired
	private StatusAlteracaoRepository statusAlteracaoRepository;
	
	@Autowired
	private EncomendaRepository encomendaRepository;
	
	public StatusAlteracao findById(Integer id) {
		Optional<StatusAlteracao> obj = statusAlteracaoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Encomenda não encontrada! Código : " + id + ", Tipo : " + Encomenda.class.getName(), null));
	}
	
	public List<StatusAlteracao> findByEmcomendaId(Integer emcomendaId) {
		
		Optional<Encomenda> encomenda1 = encomendaRepository.findById(emcomendaId);
		return statusAlteracaoRepository.findByEncomenda(encomenda1.get());
	}
}
