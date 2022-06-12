package br.edu.unifaj.poo.aps.entity.dao;

import java.io.Serializable;

public class ReceitaDao implements Serializable {

    private static final long serialVersionUID = 7205827931853913763L;

    private Integer id;
    private String nome;
    private Float tempoPreparo;
    private String passos;
    private UsuarioDao usuario;

    public ReceitaDao(Integer id, String nome, Float tempoPreparo, String passos, UsuarioDao usuario) {
        this.id = id;
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.passos = passos;
        this.usuario = usuario;
    }

    public ReceitaDao(String nome, Float tempoPreparo, String passos, UsuarioDao usuario) {
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.passos = passos;
        this.usuario = usuario;
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

    public UsuarioDao getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDao usuario) {
        this.usuario = usuario;
    }
}
