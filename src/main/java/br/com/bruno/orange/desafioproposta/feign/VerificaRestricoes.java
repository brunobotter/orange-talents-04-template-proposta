package br.com.bruno.orange.desafioproposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.bruno.orange.desafioproposta.cartao.SolicitacaoCartaoRequest;
import br.com.bruno.orange.desafioproposta.cartao.SolicitacaoCartaoResponse;

@FeignClient(url = "http://127.0.0.1:9999/api/solicitacao", name = "analise")
public interface VerificaRestricoes {

	@PostMapping
	public SolicitacaoCartaoResponse verificaRestricao(SolicitacaoCartaoRequest request);
}
