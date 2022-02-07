package com.template.spring.core.security.entities;


public class UserRole {

    public Integer id;

    public String codigo;

    public String nome;

    public String icone;

    public String paginaInicial;

    public UserRole(Integer id, String codigo, String nome, String icone, String paginaInicial) {

        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.icone = icone;
        this.paginaInicial = paginaInicial;
    }
}
