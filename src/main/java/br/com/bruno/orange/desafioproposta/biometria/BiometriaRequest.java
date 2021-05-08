package br.com.bruno.orange.desafioproposta.biometria;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Base64;
import java.util.Optional;

public class BiometriaRequest {

    @NotNull
    @NotBlank
    private String fingerPrint;

    public Biometria toModel(Optional<Cartao> cartao) {
        byte[] biometria64 = Base64.getEncoder().encode(fingerPrint.getBytes());
        return new Biometria(cartao.get(), biometria64);
    }

    public String getFingerPrint() {
        return fingerPrint;
    }
}
