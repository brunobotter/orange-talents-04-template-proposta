package br.com.bruno.orange.desafioproposta.bloqueio;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@RequestMapping("bloqueio")
@RestController
public class BloquerioController {
    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @PostMapping("/{id}")
    public ResponseEntity<?> bloquearCartao(@PathVariable("id")Long id,HttpServletRequest request,
                                            BloqueioRequest bloqueioRequest, UriComponentsBuilder uriComponentsBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(cartao.get().getBloqueio() != null){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Cartao ja bloqueado");
        }
        Bloqueio bloqueio = bloqueioRequest.toModel(cartao,request);
        bloqueioRepository.save(bloqueio);
        URI uri = uriComponentsBuilder.path("/bloqueio/{id}").build(bloqueio.getId());
        return ResponseEntity.created(uri).build();
    }
}
