package br.com.bruno.orange.desafioproposta.carteira;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String email;

    @ManyToOne
    private Cartao cartao;

    @Enumerated(EnumType.STRING)
    private IdenticadorCarteira identificadorCarteira;

    private String assossiacaoId;

    @Deprecated
    public Carteira() {
    }


    public Carteira(String email, Cartao cartao, String id, IdenticadorCarteira carteira) {
        this.email = email;
        this.cartao = cartao;
        this.identificadorCarteira = carteira;
        this.assossiacaoId = id;
    }


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public IdenticadorCarteira getIdentificadorCarteira() {
        return identificadorCarteira;
    }

    public String getAssossiacaoId() {
        return assossiacaoId;
    }
}
