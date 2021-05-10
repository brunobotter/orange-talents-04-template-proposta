package br.com.bruno.orange.desafioproposta.bloqueio;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.CartaoRepository;
import br.com.bruno.orange.desafioproposta.cartao.RestricaoCartao;
import br.com.bruno.orange.desafioproposta.feign.Cartoes;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RequestMapping("bloqueio")
@RestController
public class BloquerioController {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private Cartoes cartoes;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> bloquearCartao(@PathVariable("id")Long id, HttpServletRequest httpRequest,
                                            @RequestBody @Valid BloqueioRequest bloqueioRequest, UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(cartao.get().getStatusCartao() == BloqueioCartao.BLOQUEADO){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Cartao ja bloqueado");
        }
        return solicitarBloqueio(cartao.get(), bloqueioRequest,httpRequest,uriComponentsBuilder);
    }

    public ResponseEntity<?> solicitarBloqueio(Cartao cartao, @RequestBody @Valid BloqueioRequest request, HttpServletRequest httpRequest, UriComponentsBuilder uriComponentsBuilder) {
        try {
            Bloqueio bloqueio = new Bloqueio(cartao, httpRequest.getLocalAddr(), httpRequest.getHeader("User-Agent"));
            bloqueio.bloquearCartao(cartao);
            bloqueio = bloqueioRepository.save(bloqueio);
            cartoes.bloqueioCartao(cartao.getNumeroCartao(), new BloqueioRequest(request));
            URI uri = uriComponentsBuilder.path("/bloqueio/{id}").build(bloqueio.getId());
            return ResponseEntity.created(uri).build();
        } catch (FeignException.UnprocessableEntity e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Falha ao bloquear o cartão.");
        }
    }
}
