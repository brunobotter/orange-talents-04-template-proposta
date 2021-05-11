package br.com.bruno.orange.desafioproposta.proposta;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.bruno.orange.desafioproposta.cartao.RestricaoCartao;
import br.com.bruno.orange.desafioproposta.cartao.SolicitacaoCartaoRequest;
import br.com.bruno.orange.desafioproposta.feign.VerificaRestricoes;
import br.com.bruno.orange.desafioproposta.validacao.ExisteCpfOuCnpj;
import feign.FeignException;

@RestController
@RequestMapping("propostas")
public class PropostaController {

	@Autowired
	private ExisteCpfOuCnpj existeCpfOuCnpj;

	@Autowired
	private PropostaRepository repository;
	private final Tracer tracer;
	@Autowired
	private VerificaRestricoes verificaRestricoes;

	public PropostaController(ExisteCpfOuCnpj existeCpfOuCnpj, PropostaRepository repository,
							  Tracer tracer, VerificaRestricoes verificaRestricoes) {
		super();
		this.existeCpfOuCnpj = existeCpfOuCnpj;
		this.repository = repository;
		this.tracer = tracer;
		this.verificaRestricoes = verificaRestricoes;
	}

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(existeCpfOuCnpj);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PropostaResponse> salvarProposta(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriComponentsBuilder) {
		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("user.email", "bruno.vales@zup.com.br");
		Proposta proposta = request.toModel();
		repository.save(proposta);
		verificaRestricao(proposta);
		URI uri = uriComponentsBuilder.path("/propostas/{id}").build(proposta.getId());
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponse> consultaProposta(@PathVariable("id") Long id){
		Optional<Proposta> proposta = repository.findById(id);
		if(proposta.isPresent()){
			return ResponseEntity.ok(new PropostaResponse(proposta));
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}

	private void verificaRestricao(Proposta proposta) {
		try {
			SolicitacaoCartaoRequest cartaoRequest = proposta.toModelCartao();
			verificaRestricoes.verificaRestricao(cartaoRequest);
			proposta.adicionaRestricao(RestricaoCartao.ELEGIVEL);
			repository.save(proposta);
		}catch (FeignException.UnprocessableEntity e) {
			proposta.adicionaRestricao(RestricaoCartao.NAO_ELEGIVEL);		
			repository.save(proposta);
		}
	}

}
