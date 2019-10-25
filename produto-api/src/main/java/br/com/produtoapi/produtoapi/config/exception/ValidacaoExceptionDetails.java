package br.com.produtoapi.produtoapi.config.exception;

import lombok.Data;

@Data
public class ValidacaoExceptionDetails {

    private long timestamp;
    private int status;
    private String error;
    private String message;

}
