package br.com.alura.comex.config;

public class BussinesException extends RuntimeException{

    public BussinesException(String message) {
        super(message,null,false,false);
    }
}
