package br.edu.unifaj.poo.aps.entity;

public class Retorno {

    public String status;
    public String mensagemErro;
    public Object object;

    public Retorno(Object obj) {
        this.status = "OK";
        this.object = obj;
    }

    public Retorno(String erro) {
        this.status = "Erro";
        this.mensagemErro = erro;
    }

}
