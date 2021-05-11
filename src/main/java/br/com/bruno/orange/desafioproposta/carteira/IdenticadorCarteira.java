package br.com.bruno.orange.desafioproposta.carteira;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum IdenticadorCarteira {

    PAYPAL;

    @JsonCreator
    public static IdenticadorCarteira fromString(String value) {
        if (value == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value");
        for (var identificadorCarteira : values()) {
            if (value.equalsIgnoreCase(identificadorCarteira.toString())) {
                return identificadorCarteira;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value");
    }
}
