package com.bank.bank.models;

import jakarta.persistence.*;

@Entity
// Indica que essa classe Java é uma entidade que será mapeada para uma tabela no banco de dados.
@Table(name = "contas")
//permite customizar o nome da tabela, o esquema e outras características do mapeamento

//Ambos tanto o Entity quanto o Tablea trabalham sempre juntos.

public abstract class Contas {

    @Column(name = "saldo")
    private double saldo;

    @Override
    public String toString() {
        return "Contas: " +
                "saldo= " + saldo +
                ", numero= '" + numero + '\'' +
                ", cpfCnpj= '" + cpfCnpj + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;


    public Contas(String numero, String cpfCnpj, Double saldo) {
        this.numero = numero;
        this.cpfCnpj = cpfCnpj;
        this.saldo= saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
