package br.com.bruno.orange.desafioproposta.feign;

public class BloqueioCartaoResponse {


    private String status;

    public BloqueioCartaoResponse(String status) {
        this.status = status;
    }

    @Deprecated
    public BloqueioCartaoResponse() {
    }

    public String getStatus() {
        return status;
    }
}
