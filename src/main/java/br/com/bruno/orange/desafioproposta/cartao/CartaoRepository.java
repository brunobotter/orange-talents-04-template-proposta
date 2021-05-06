package br.com.bruno.orange.desafioproposta.cartao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface CartaoRepository extends CrudRepository<Cartao, Long> {
}
