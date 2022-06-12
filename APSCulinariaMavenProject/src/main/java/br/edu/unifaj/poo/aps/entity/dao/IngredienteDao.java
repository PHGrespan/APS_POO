package br.edu.unifaj.poo.aps.entity.dao;

import java.io.Serializable;

public class IngredienteDao implements Serializable {

    private static final long serialVersionUID = 3963639339164251539L;

    private Integer id;
    private String nome;

    public IngredienteDao(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
