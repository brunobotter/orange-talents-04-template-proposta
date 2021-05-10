package br.com.bruno.orange.desafioproposta.bloqueio;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class BloqueioRequest {

    private String ipCliente;
    private String userAgent;

    public BloqueioRequest(String ipCliente, String userAgent) {
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
    }


    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }


    public Bloqueio toModel(Optional<Cartao> cartao, HttpServletRequest request) {
        return new Bloqueio(cartao,request.getRemoteAddr(), request.getHeader("User-Agent"));
    }
}
