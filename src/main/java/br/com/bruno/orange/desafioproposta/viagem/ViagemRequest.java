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
    private String destinoViagem;

    @Future
    private LocalDate terminoViagem;

    public ViagemRequest(Cartao cartao, String destinoViagem, LocalDate terminoViagem) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
    }


    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public Viagem toModel( HttpServletRequest servlet) {
        return new Viagem(servlet, destinoViagem, terminoViagem);
    }
}
