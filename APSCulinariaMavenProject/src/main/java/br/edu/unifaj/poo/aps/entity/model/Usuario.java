package br.edu.unifaj.poo.aps.entity.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = -9013433782923095125L;

    private String cpf;
    private String nome;

    public Usuario(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
