package br.edu.unifaj.poo.aps.entity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Receita implements Serializable {

    private static final long serialVersionUID = -8537712035580603247L;

    private String nome;
    private Float tempoPreparo;
    private String passos;
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public Receita(String nome, Float tempoPreparo, String passos, List<Ingrediente> ingredientes) {
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.passos = passos;
        this.ingredientes = ingredientes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(Float tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public String getPassos() {
        return passos;
    }

    public void setPassos(String passos) {
        this.passos = passos;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
