package br.com.bruno.orange.desafioproposta.feign;

import br.com.bruno.orange.desafioproposta.cartao.CartaoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://127.0.0.1:8888", name = "cartoes")
public interface Cartoes {

    /*
     *  Executa uma chamada GET na rota /api/cartoes do serviço de accounts,
     * retornando a resposta como um String.
     * @param idProposta id da proposta para qual procura-se um cartão.
     * @return CartaoClientResponse objeto representando o cartão encontrado.
     */
    @GetMapping("/api/cartoes")
    public CartaoClientResponse cartaoParaProposta(@RequestParam(name = "idProposta") String idProposta);
}
