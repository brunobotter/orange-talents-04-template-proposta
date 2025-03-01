package br.com.bruno.orange.desafioproposta.feign;

import br.com.bruno.orange.desafioproposta.bloqueio.BloqueioRequest;
import br.com.bruno.orange.desafioproposta.cartao.CartaoClientResponse;
import br.com.bruno.orange.desafioproposta.carteira.CarteiraRequest;
import br.com.bruno.orange.desafioproposta.carteira.CarteiraResponse;
import br.com.bruno.orange.desafioproposta.viagem.AvisoViagemResponse;
import br.com.bruno.orange.desafioproposta.viagem.ViagemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${cartoes.host}", name = "cartoes")
public interface Cartoes {

    /*
     *  Executa uma chamada GET na rota /api/cartoes do serviço de accounts,
     * retornando a resposta como um String.
     * @param idProposta id da proposta para qual procura-se um cartão.
     * @return CartaoClientResponse objeto representando o cartão encontrado.
     */
    @GetMapping
    public CartaoClientResponse cartaoParaProposta(@RequestParam(name = "idProposta") String idProposta);

    @PostMapping("/{id}/bloqueios")
    public BloqueioCartaoResponse bloqueioCartao(@PathVariable(name = "id") String id, @RequestBody BloqueioRequest request);

    @PostMapping("/{id}/avisos")
    public AvisoViagemResponse avisoViagem(@PathVariable(name = "id") String id, @RequestBody ViagemRequest request);

    @PostMapping("/{id}/carteiras")
    public CarteiraResponse carteira(@PathVariable(name = "id") String id, @RequestBody CarteiraRequest request);

}
