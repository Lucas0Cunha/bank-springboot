package com.bank.bank.models;

import jakarta.persistence.*;

@Entity
// Indica que essa classe Java é uma entidade que será mapeada para uma tabela no banco de dados.

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
// @DiscriminatorColumn: Basicamente define a coluna que servirá para diferenciar as diferentes classes da hierarquia, neste caso a coluna tipo-conta vai receber as filhas
// contacredito,contadebito....

/*@Table(name = "contas")
//permite customizar o nome da tabela, o esquema e outras características do mapeamento

//Ambos tanto o Entity quanto o Tablea trabalham sempre juntos.*/

public abstract class Contas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "saldo")
    private double saldo;

    @Column(name = "numero", unique = true)
    private String numero;

    @ManyToOne
    //
    @JoinColumn(name = "id_agencia", referencedColumnName = "id")
    private Agencias agencias;

    @ManyToOne
    // Define o relacionamento entre classes, neste caso posso ter VARIAS contas para um cliente, mas n varias clientes para um conta
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    // O join column vem logo em seguida para estabelecer a conexão entre estas tabelas com algum fator relacionao
    private Cliente cliente;
// key

    public Contas() {
    }

    public Contas(String numero, Cliente cliente, Double saldo) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
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


    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Contas{" +
                "saldo=" + saldo +
                ", id=" + id +
                ", numero='" + numero + '\'' +
                ", cliente=" + cliente +
                '}';
    }


}


