package br.com.bruno.orange.desafioproposta.carteira;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import java.util.Optional;

public class CarteiraRequest {

    private String email;
    private IdenticadorCarteira carteira;

    @Deprecated
    public CarteiraRequest() {
    }

    public CarteiraRequest(String email, IdenticadorCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public IdenticadorCarteira getCarteira() {
        return carteira;
    }

    public Carteira toModel(Cartao cartao, String id) {
        return new Carteira(email, cartao, id, carteira);
    }
}
