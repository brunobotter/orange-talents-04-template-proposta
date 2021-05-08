package br.com.bruno.orange.desafioproposta.biometria;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    private LocalDateTime dataCadastroBiometria;

    @NotNull
    private byte[] fingerPrint;

    public Biometria(Long id, Cartao cartao, LocalDateTime dataCadastroBiometria, byte[] fingerPrint) {
        this.id = id;
        this.cartao = cartao;
        this.dataCadastroBiometria = LocalDateTime.now();
        this.fingerPrint = fingerPrint;
    }

    public Biometria(Cartao cartao, byte[] fingerPrint) {
        this.cartao = cartao;
        this.fingerPrint = fingerPrint;
        this.dataCadastroBiometria = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getDataCadastroBiometria() {
        return dataCadastroBiometria;
    }

    public byte[] getFingerPrint() {
        return fingerPrint;
    }
}
