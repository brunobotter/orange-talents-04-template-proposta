package br.com.bruno.orange.desafioproposta.bloqueio;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class BloqueioRequest {

    @NotNull
   private String sistemaResponsavel;

    @Deprecated
    public BloqueioRequest() {
    }

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public BloqueioRequest(BloqueioRequest request) {
        this.sistemaResponsavel = request.getSistemaResponsavel();
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public Bloqueio toModel(Cartao cartao, String ip, String userAgent) {
        return new Bloqueio(cartao,ip, userAgent);
    }
}
