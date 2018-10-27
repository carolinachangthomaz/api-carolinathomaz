package com.carolinathomaz.apicarolinathomaz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carolinathomaz.apicarolinathomaz.model.Embalagem;
import com.carolinathomaz.apicarolinathomaz.model.Encomenda;

public interface EncomendaRepository extends JpaRepository<Encomenda, Integer>{

}
