package br.com.bruno.orange.desafioproposta.bloqueio;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueioRepository extends CrudRepository<Bloqueio, Long> {

    Optional<Bloqueio> findByCartao(Cartao cartao);
}
