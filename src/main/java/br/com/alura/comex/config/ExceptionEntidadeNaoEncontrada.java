package br.com.alura.comex.config;

public class ExceptionEntidadeNaoEncontrada extends RuntimeException{

    public  ExceptionEntidadeNaoEncontrada(String mensagem){
       super(mensagem,null,false,false);
    }
}
