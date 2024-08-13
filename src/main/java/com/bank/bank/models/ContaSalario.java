package com.bank.bank.models;

import com.bank.bank.util.TipoConta;

public class ContaSalario extends Contas{
    public ContaSalario(String numero, String cpfCnpj, double saldo) {
        super(numero, cpfCnpj, saldo);
    }


    public TipoConta getTipoConta(){
        return TipoConta.SALARIO;
    }
}
