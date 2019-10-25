package br.com.usuarioapi.usuarioapi.exceptions;

import lombok.Data;

@Data
public class ValidacaoExceptionDetails {

    private long timestamp;
    private int status;
    private String error;
    private String message;

}
