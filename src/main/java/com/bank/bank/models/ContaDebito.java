package com.bank.bank.models;

import com.bank.bank.util.TipoConta;

public class ContaDebito extends Contas{

    public ContaDebito(String numero, String cpfCnpj, double saldo) {
        super(numero, cpfCnpj, saldo);
    }

    public TipoConta getTipoConta(){
        return TipoConta.DEBITO;
    }


}
