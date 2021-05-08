package br.com.bruno.orange.desafioproposta.biometria;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RequestMapping("biometria")
@RestController
public class BiometriaController {


    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping("{id}")
    @Transactional
    public ResponseEntity<?> salvarBiomatria(@PathVariable("id") Long id,
                                             @RequestBody @Valid BiometriaRequest request,
                                             UriComponentsBuilder uriComponentsBuilder  ){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Biometria biometria = request.toModel(cartao);
        biometriaRepository.save(biometria);
        URI uri = uriComponentsBuilder.path("/biometria/{id}").build(biometria.getId());
        return ResponseEntity.created(uri).build();
    }
}
