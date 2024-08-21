package com.bank.bank.models;

import jakarta.persistence.*;

@Entity
@Table(name = "agencia")
public class Agencias {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "estado", unique = true)
    private String estado;


    @Column
    private String nomeAgencia;



    public Agencias() {
    }

    public Agencias(Long id, String estado, String nomeAgencia) {
        this.id = id;
        this.estado = estado;
        this.nomeAgencia = nomeAgencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }
}
