package com.carolinathomaz.apicarolinathomaz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carolinathomaz.apicarolinathomaz.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
