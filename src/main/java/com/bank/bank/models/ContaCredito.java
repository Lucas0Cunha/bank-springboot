package com.bank.bank.models;

import com.bank.bank.util.TipoConta;
import jakarta.persistence.*;

@Entity
// Indica que essa classe Java é uma entidade que será mapeada para uma tabela no banco de dados.
@DiscriminatorValue("contacredito")
public class ContaCredito extends Contas{

    private double limite;



    private String dataValidade;

    public ContaCredito(String numero, Cliente cliente, double saldo) {
        super(numero, cliente, saldo);
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
