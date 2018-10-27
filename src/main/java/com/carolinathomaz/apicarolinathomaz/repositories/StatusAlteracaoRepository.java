package com.carolinathomaz.apicarolinathomaz.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.carolinathomaz.apicarolinathomaz.model.Encomenda;
import com.carolinathomaz.apicarolinathomaz.model.StatusAlteracao;

public interface StatusAlteracaoRepository extends JpaRepository<StatusAlteracao, Integer>{
	
	@Transactional(readOnly=true)
	List<StatusAlteracao> findByEncomenda(Encomenda encomenda);
	
}
