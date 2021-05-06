package br.com.bruno.orange.desafioproposta.proposta;

import java.util.List;
import java.util.Optional;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.RestricaoCartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long>{

	Optional<Proposta> findByCpfCnpj(String cpfCnpj);

	List<Proposta> findByRestricaoAndCartao(RestricaoCartao restricao, Cartao cartao);
}
