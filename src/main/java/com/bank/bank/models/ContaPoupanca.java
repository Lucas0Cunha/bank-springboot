package com.bank.bank.models;

import com.bank.bank.util.TipoConta;
import jakarta.persistence.*;


@Entity
// Indica que essa classe Java é uma entidade que será mapeada para uma tabela no banco de dados.
@DiscriminatorValue("contapoupanca")
public class ContaPoupanca extends Contas{
    public ContaPoupanca(String numero, Cliente cliente, double saldo, Agencias clienteAgencia) {
        super(numero, cliente, saldo, clienteAgencia);
    }

    public ContaPoupanca() {
    }

    public TipoConta getTipoConta(){
        return TipoConta.POUPANCA;
    }



}
