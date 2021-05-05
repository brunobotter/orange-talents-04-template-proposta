package br.com.bruno.orange.desafioproposta.proposta;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bruno.orange.desafioproposta.validacao.ExisteCpfOuCnpj;

@RestController
@RequestMapping("proposta")
public class PropostaController {

	@Autowired
	private ExisteCpfOuCnpj existeCpfOuCnpj;

	@Autowired
	private PropostaRepository repository;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(existeCpfOuCnpj);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PropostaResponse> salvarProposta(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriComponentsBuilder) {
		Proposta proposta = request.toModel();
		repository.save(proposta);
		URI uri = uriComponentsBuilder.path("/propostas/{id}").build(proposta.getId());
		return ResponseEntity.created(uri).build();
	}

}
