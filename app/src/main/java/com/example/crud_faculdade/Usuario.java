package com.example.crud_faculdade;

import java.io.Serializable;

public class Usuario implements Serializable {
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getIdade() {
        return Idade;
    }

    public void setIdade(String idade) {
        Idade = idade;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    private Integer Id;
    private String Nome;
    private String CPF;
    private String Idade;
    private String Sexo;
    private String Email;
    private String Telefone;


    @Override
    public String toString() {
        return Nome;
    }


}
