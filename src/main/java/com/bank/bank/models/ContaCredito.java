package com.bank.bank.models;

import com.bank.bank.util.TipoConta;

public class ContaCredito extends Contas{

    private double limite;

    private String dataValidade;

    public ContaCredito(String numero, String cpfCnpj, double saldo) {
        super(numero, cpfCnpj, saldo);
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public TipoConta getTipoConta(){
        return TipoConta.CREDITO;
    }

}
