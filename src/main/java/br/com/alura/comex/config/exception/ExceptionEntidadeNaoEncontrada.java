package br.com.alura.comex.config.exception;

public class ExceptionEntidadeNaoEncontrada extends RuntimeException{

    public  ExceptionEntidadeNaoEncontrada(String mensagem){
       super(mensagem,null,false,false);
    }
}
