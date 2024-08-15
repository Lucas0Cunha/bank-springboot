package com.bank.bank.models;

import com.bank.bank.util.TipoConta;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
// Indica que essa classe Java é uma entidade que será mapeada para uma tabela no banco de dados.
@DiscriminatorValue("contacredito")
public class ContaCredito extends Contas{

    //private double limite;

    public ContaCredito() {
    }

    private LocalDate dataValidade;

    public ContaCredito(String numero, Cliente cliente, double saldo, /*double limite,*/ LocalDate dataValidade) {
        super(numero, cliente, saldo);
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = LocalDate.parse(dataValidade);
    }

   /* public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }*/

    public TipoConta getTipoConta(){
        return TipoConta.CREDITO;
    }





}
