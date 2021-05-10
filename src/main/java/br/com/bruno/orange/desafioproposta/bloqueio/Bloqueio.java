package br.com.bruno.orange.desafioproposta.bloqueio;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @NotNull
    private Cartao cartao;

    private LocalDateTime dataBloqueio;

    @NotNull
    private String ipCliente;

    @NotNull
    private String userAgent;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(Cartao cartao, String ipCliente, String userAgent) {
        this.cartao = cartao;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.dataBloqueio = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void bloquearCartao(Cartao cartao) {
        cartao.adicionaBloqueio();
        this.cartao = cartao;
    }
}
