package br.com.bruno.orange.desafioproposta.carteira;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import java.util.Optional;

public class CarteiraResponse {

    private String resultado;

    private String id;

    @Deprecated
    public CarteiraResponse() {
    }

    public CarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }


}
