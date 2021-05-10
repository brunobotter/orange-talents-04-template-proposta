package br.com.bruno.orange.desafioproposta.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.bruno.orange.desafioproposta.cartao.SolicitacaoCartaoRequest;
import br.com.bruno.orange.desafioproposta.cartao.SolicitacaoCartaoResponse;

@FeignClient(url = "${analises.host}", name = "analises")
public interface VerificaRestricoes {

	@PostMapping
	public SolicitacaoCartaoResponse verificaRestricao(SolicitacaoCartaoRequest request);
}
