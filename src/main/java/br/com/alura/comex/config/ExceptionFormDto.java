package br.com.alura.comex.config;

public class ExceptionFormDto {

    private String campo;
    private String erro;

    public ExceptionFormDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
