package com.carolinathomaz.apicarolinathomaz.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carolinathomaz.apicarolinathomaz.model.StatusAlteracao;
import com.carolinathomaz.apicarolinathomaz.services.StatusAlteracaoService;

/*
 * Endpoint para rastrear a encomenda
 * @CrossOrigin("*") - liberando para a qualquer cliente HTTP
 * 
 */

@CrossOrigin("*")
@RestController
@RequestMapping(value="/rastreador")
public class RastreadorResource {
	
	@Autowired
	private StatusAlteracaoService statusAlteracaoService;
	
	@RequestMapping(value="/{emcomendaId}" ,method=RequestMethod.GET)
	public ResponseEntity<?> rastrearEncomenda(@PathVariable Integer emcomendaId) {
		List<StatusAlteracao> lista = statusAlteracaoService.findByEmcomendaId(emcomendaId);
		return ResponseEntity.ok(lista);
	}

}
