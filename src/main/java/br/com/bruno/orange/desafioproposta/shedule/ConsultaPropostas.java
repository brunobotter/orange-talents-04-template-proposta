package br.com.bruno.orange.desafioproposta.shedule;

import br.com.bruno.orange.desafioproposta.cartao.CartaoClientResponse;
import br.com.bruno.orange.desafioproposta.cartao.RestricaoCartao;
import br.com.bruno.orange.desafioproposta.feign.Cartoes;
import br.com.bruno.orange.desafioproposta.proposta.Proposta;
import br.com.bruno.orange.desafioproposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ConsultaPropostas {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private Cartoes cartao;

    public ConsultaPropostas(PropostaRepository propostaRepository, Cartoes cartao) {
        this.propostaRepository = propostaRepository;
        this.cartao = cartao;
    }
   @Scheduled(fixedDelayString = "${periodicidade.tentativa-numero-cartao}")
    public void consultaPropostasElegiveis() {
       List<Proposta> proposta = propostaRepository.findByRestricaoAndCartao(RestricaoCartao.ELEGIVEL, null);
      try{
           for (Proposta lista: proposta ) {
               CartaoClientResponse novoCartao = cartao.cartaoParaProposta(lista.getId().toString());
               lista.adicionaCartao(novoCartao.toModel(lista));
               propostaRepository.save(lista);
           }
       }catch (FeignException.UnprocessableEntity e){
          e.printStackTrace();
      }
    }
}
