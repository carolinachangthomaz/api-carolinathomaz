package com.carolinathomaz.apicarolinathomaz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carolinathomaz.apicarolinathomaz.model.Embalagem;

public interface EncomendaRepository extends JpaRepository<Embalagem, Integer>{

}
