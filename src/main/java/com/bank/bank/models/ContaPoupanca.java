package com.bank.bank.models;

import com.bank.bank.util.TipoConta;

public class ContaPoupanca extends Contas{
    public ContaPoupanca(String numero, String cpfCnpj, double saldo) {
        super(numero, cpfCnpj, saldo);
    }


    public TipoConta getTipoConta(){
        return TipoConta.POUPANCA;
    }
}
