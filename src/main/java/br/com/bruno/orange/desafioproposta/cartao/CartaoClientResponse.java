package br.com.bruno.orange.desafioproposta.cartao;

import br.com.bruno.orange.desafioproposta.proposta.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CartaoClientResponse {


    @NotBlank
    private String id;

    @NotNull
    @Positive
    private Long idProposta;


    public CartaoClientResponse(String id, Long idProposta) {
        this.id = id;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public Cartao toModel(Proposta proposta) {
        return new Cartao(id, proposta);
    }


}
