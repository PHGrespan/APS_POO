package br.edu.unifaj.poo.aps.entity.dao;

import java.io.Serializable;

public class AvaliacaoDao implements Serializable {

    private static final long serialVersionUID = 7237760063437898746L;

    private Integer id;
    private ReceitaDao receita;
    private UsuarioDao usuario;
    private String descricao;
    private Float nota;

    public AvaliacaoDao(Integer id, ReceitaDao receita, UsuarioDao usuario, String descricao, Float nota) {
        this.id = id;
        this.receita = receita;
        this.usuario = usuario;
        this.descricao = descricao;
        this.nota = nota;
    }

    public AvaliacaoDao(ReceitaDao receita, UsuarioDao usuario, String descricao, Float nota) {
        this.receita = receita;
        this.usuario = usuario;
        this.descricao = descricao;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReceitaDao getReceita() {
        return receita;
    }

    public void setReceita(ReceitaDao receita) {
        this.receita = receita;
    }

    public UsuarioDao getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDao usuario) {
        this.usuario = usuario;
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

