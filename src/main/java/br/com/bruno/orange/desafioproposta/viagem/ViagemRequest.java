package br.com.bruno.orange.desafioproposta.viagem;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemRequest {


    @NotNull
    @NotBlank
    private String destino;

    @Future
    private LocalDate validoAte;

    public ViagemRequest(Cartao cartao, String destinoViagem, LocalDate terminoViagem) {
        this.destino = destinoViagem;
        this.validoAte = terminoViagem;
    }


    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public Viagem toModel(HttpServletRequest servlet) {
        return new Viagem(servlet, destino, validoAte);
    }
}
