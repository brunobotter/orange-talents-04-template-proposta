package br.com.bruno.orange.desafioproposta.proposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("proposta")
public class PropostaController {

	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PropostaResponse> salvarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
		Proposta proposta = request.toModel();
		manager.persist(proposta);
		return ResponseEntity.created(uriComponentsBuilder.path("proposta/{id}").buildAndExpand(proposta.getId()).toUri()).body(new PropostaResponse(proposta));
	}
	
	/*@PostMapping
	@Transactional
	public ResponseEntity<PropostaResponse> salvarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriComponentsBuilder){
	Proposta proposta = request.toModel();
	manager.persist(proposta);
	URI uri =  uriComponentsBuilder.path("/propostas/{id}").build(proposta.getId());
	return ResponseEntity.created(uri).build();
	}*/
}
