package com.kaio.usuario.infrastructure.exceptions;

public class ResouceNotFoundException extends RuntimeException {
    public ResouceNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ResouceNotFoundException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
