package br.com.bruno.orange.desafioproposta.viagem;

import br.com.bruno.orange.desafioproposta.cartao.StatusCartao;
import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.CartaoRepository;
import br.com.bruno.orange.desafioproposta.feign.Cartoes;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("viagem")
public class ViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private Cartoes cartoes;

    @PostMapping("/{id}")
    public ResponseEntity<AvisoViagemResponse> avisoViagem(@PathVariable("id") Long id, @Valid @RequestBody ViagemRequest request,
                                         HttpServletRequest servlet){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao nao encontrado");
        }
        if(cartao.get().getStatusCartao() == StatusCartao.BLOQUEADO){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cartao esta bloqueado");
        }
        if(cartao.get().getStatusCartao() == StatusCartao.VIAJANDO){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO o status do cartao ja esta como viagem");
        }
        try{
            AvisoViagemResponse aviso = cartoes.avisoViagem(cartao.get().getNumeroCartao(),request);
            cartao.get().adicionaViagem(request.toModel(servlet));
            cartaoRepository.save(cartao.get());
            return ResponseEntity.ok(aviso);
        }catch (FeignException.UnprocessableEntity e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nao armazenado!");
        }

    }

}
