package br.com.alura.comex.config.exception;

public class BussinesException extends RuntimeException{

    public BussinesException(String message) {
        super(message,null,false,false);
    }
}
