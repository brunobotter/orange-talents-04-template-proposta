package br.com.bruno.orange.desafioproposta.carteira;


import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.CartaoRepository;
import br.com.bruno.orange.desafioproposta.feign.Cartoes;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("carteira")
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private Cartoes cartoes;

    @PostMapping("/{id}")
    public ResponseEntity<?> adicionar(@PathVariable("id") Long id,
                                       @Valid @RequestBody CarteiraRequest request,
                                       UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao nao encontrado");
        }
        Optional<Carteira> carteira = carteiraRepository.findByCartaoAndIdentificadorCarteira(cartao.get(), request.getCarteira());
        if(carteira.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira ja assossiada a este cartao");
        }
        try{
        CarteiraResponse carteiraResponse = cartoes.carteira(cartao.get().getNumeroCartao(), request);
        Carteira novaCarteira = carteiraRepository.save(request.toModel(cartao.get(), carteiraResponse.getId()));
            URI uri = uriComponentsBuilder.path("/carteira/{id}").build(novaCarteira.getId());
            return ResponseEntity.created(uri).build();
        }catch (FeignException.FeignClientException | FeignException.FeignServerException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
