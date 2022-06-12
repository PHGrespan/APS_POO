package br.edu.unifaj.poo.aps.entity.dao;

import java.io.Serializable;

public class IngredienteReceitaDao implements Serializable {

    private static final long serialVersionUID = -6698736426374226882L;

    private IngredienteDao ingrediente;
    private ReceitaDao receita;
    private String quantidade;

    public IngredienteReceitaDao(IngredienteDao ingrediente, ReceitaDao receita, String quantidade) {
        this.ingrediente = ingrediente;
        this.receita = receita;
        this.quantidade = quantidade;
    }

    public IngredienteDao getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDao ingrediente) {
        this.ingrediente = ingrediente;
    }

    public ReceitaDao getReceita() {
        return receita;
    }

    public void setReceita(ReceitaDao receita) {
        this.receita = receita;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
