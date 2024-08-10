package com.bank.bank.models;

import jakarta.persistence.*;

@Entity
//
@Table (name = "cliente")
//
public class Cliente {
    @Id //
    @GeneratedValue (strategy = GenerationType.AUTO) //
    private Long id;
    private String email;
    private String nome;
    @Column (name = "cpf_cnpj") //
    private String cpfCnpj;

    public Cliente() {
    }

    public Cliente(String name, String cpfCnpj) {
        this.setNome(name);
        this.setCpfCnpj(cpfCnpj);
    }


    public Cliente(String email, String name,String cpfCnpj) {
        this.setEmail(email);
        this.setNome(name);
        this.setCpfCnpj(cpfCnpj);
    }

    public Cliente(String name, Long id) {
        this.setNome(name);
        this.setId(id);
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", email=" + email + ", name=" + nome + ", cpfCnpj=" + cpfCnpj + "]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
