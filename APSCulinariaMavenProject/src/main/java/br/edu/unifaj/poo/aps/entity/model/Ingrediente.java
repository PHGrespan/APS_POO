package br.edu.unifaj.poo.aps.entity.model;

import java.io.Serializable;

public class Ingrediente implements Serializable {

    private static final long serialVersionUID = -4383563049301068205L;

    private String nome;
    private String quantidade;

    public Ingrediente(String nome, String quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
