package br.edu.unifaj.poo.aps.entity.model;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 7654778088702409795L;

    private String nomeUsuario;
    private String descricao;
    private Float nota;

    public Avaliacao(String nomeUsuario, String descricao, Float nota) {
        this.nomeUsuario = nomeUsuario;
        this.descricao = descricao;
        this.nota = nota;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }
}

