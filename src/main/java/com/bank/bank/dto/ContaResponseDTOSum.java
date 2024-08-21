package com.bank.bank.dto;

public class ContaResponseDTOSum {

    private String nome;
    private Double saldo;

    public ContaResponseDTOSum() {
    }

    public ContaResponseDTOSum(String nome, Double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
