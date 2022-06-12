package br.edu.unifaj.poo.aps.entity.dao;

import java.io.Serializable;

public class UsuarioDao implements Serializable {

    private static final long serialVersionUID = -5177950722448104547L;

    private Integer id;
    private String cpf;
    private String nome;

    public UsuarioDao(Integer id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    public UsuarioDao(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
