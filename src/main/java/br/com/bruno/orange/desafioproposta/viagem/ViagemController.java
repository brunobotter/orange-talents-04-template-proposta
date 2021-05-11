package br.com.bruno.orange.desafioproposta.viagem;

import br.com.bruno.orange.desafioproposta.cartao.BloqueioCartao;
import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.CartaoRepository;
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

    @PostMapping("/{id}")
    public ResponseEntity<?> avisoViagem(@PathVariable("id") Long id, @Valid @RequestBody ViagemRequest request,
                                         HttpServletRequest servlet){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao nao encontrado");
        }
        if(cartao.get().getStatusCartao() == BloqueioCartao.BLOQUEADO){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartao esta bloqueado");
        }
        cartao.get().adicionaViagem(request.toModel(servlet));
        cartaoRepository.save(cartao.get());
        return ResponseEntity.ok("Aviso viagem cadastrado com sucesso!");
    }
}
