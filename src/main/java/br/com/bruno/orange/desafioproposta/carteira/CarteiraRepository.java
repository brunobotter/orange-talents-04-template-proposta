package br.com.bruno.orange.desafioproposta.carteira;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteiraRepository extends CrudRepository<Carteira, Long> {

    public Optional<Carteira> findByCartaoAndIdentificadorCarteira(Cartao cartao, IdenticadorCarteira identicadorCarteira);
}
