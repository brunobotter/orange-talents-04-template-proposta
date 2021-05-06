package br.com.bruno.orange.desafioproposta.cartao;

import br.com.bruno.orange.desafioproposta.proposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(Proposta proposta) {
        this.proposta = proposta;
    }


    public Cartao(String id, Proposta proposta) {
        this.numeroCartao = id;
        this.proposta = proposta;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
