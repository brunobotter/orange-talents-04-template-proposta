package br.com.bruno.orange.desafioproposta.viagem;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String destinoViagem;

    @Future
    private LocalDate terminoViagem;

    @NotNull
    private LocalDate instanteAvisoViagem;


    @NotNull
    @NotBlank
    private String ipCliente;

    @NotNull
    @NotBlank
    private String userAgente;

    @Enumerated(EnumType.STRING)
    private StatusViagem status;

    @Deprecated
    public Viagem() {
    }

    public Viagem(HttpServletRequest servlet, String destinoViagem, LocalDate terminoViagem) {
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
        this.instanteAvisoViagem = LocalDate.now();
        this.ipCliente = servlet.getRemoteAddr();
        this.userAgente = servlet.getHeader("User-Agent");
        this.status = StatusViagem.VIAJANDO;
    }


    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public LocalDate getInstanteAvisoViagem() {
        return instanteAvisoViagem;
    }


    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgente() {
        return userAgente;
    }

    public StatusViagem getStatus() {
        return status;
    }
}
