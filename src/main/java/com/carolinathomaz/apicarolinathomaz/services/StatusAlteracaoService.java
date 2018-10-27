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

/* Service Responsável pela comunicação com o BD 
 * - Método de buscar todas as alterações de statuas da postagem até a entrega
 * - Método para salvar status simulando 
 *                 POSTADO - cliente posta a encomenda com itens
 *                 EMTRANSITO - encomenda com destino centro de distribuição x
 *                 ENTRADA - a encomenda chega no centro de distribuição x
 *                 EMTRANSITOPARACLIENTE - encomenda com destino informado pelo cliente
 *                 ENTREGUE - encomenda entregue para o cliente é orbigatório upload de comprovante de entrega
 *                 
 */
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
        if(encomenda1.isPresent()) {
			return statusAlteracaoRepository.findByEncomenda(encomenda1.get());
		}else {
			return null;
		}
	}

	public void salvarStatus(List<StatusAlteracao> asList) {
		statusAlteracaoRepository.saveAll(asList);
		
	}
}
