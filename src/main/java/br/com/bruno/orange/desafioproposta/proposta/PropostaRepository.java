package br.com.bruno.orange.desafioproposta.proposta;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long>{

	Optional<Proposta> findByCpfCnpj(String cpfCnpj);
}
