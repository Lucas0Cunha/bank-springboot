package com.bank.bank.models;

import com.bank.bank.util.TipoConta;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
// Indica que essa classe Java é uma entidade que será mapeada para uma tabela no banco de dados.
@DiscriminatorValue("contasalario")

public class ContaSalario extends Contas{
    public ContaSalario(String numero, Cliente cliente, double saldo) {
        super(numero, cliente, saldo);
    }




    public TipoConta getTipoConta(){
        return TipoConta.SALARIO;
    }
}
