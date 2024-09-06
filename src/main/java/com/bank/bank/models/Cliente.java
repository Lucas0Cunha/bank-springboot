package com.bank.bank.models;

import jakarta.persistence.*;
// JPA é uma especificação que facilita o desenvolvimento de aplicações Java que interagem com bancos de dados relacionais

//TODO PERGUNTAR SOBRE FRAMEWORK- Ferramenta para ajudar processos ex spring boot
// TODO ESCREVER SOBRE API, REST E API RESTful
//API biblioteca, REST-


@Entity
// Indica que essa classe Java é uma entidade que será mapeada para uma tabela no banco de dados.
@Table(name = "cliente")
//permite customizar o nome da tabela, o esquema e outras características do mapeamento

//Ambos tanto o Entity quanto o Tablea trabalham sempre juntos.

public class Cliente {
    @Id
    // decorator do JPA para o entendimento no banco de dados que o prox dado era o ID, a chave primaria

    @GeneratedValue(strategy = GenerationType.AUTO)
    // O GeneratedValue define como o valor da chave primária será gerado, nesse caso automaticamente

    private Long id;
    private String email;
    private String nome;

    @Column(name = "estado")
    private String estado;


    @Column(name = "cpf_cnpj", unique = true)
    // Do JPA é usado para especificar mais o detalhamento da tabela do banco de dados, podendo até definir mais precisamente como: nullable = false, length = 100, unique = true...
    private String cpfCnpj;

    public Cliente() {
    }

    public Cliente(String name, String cpfCnpj) {
        this.setNome(name);
        this.setCpfCnpj(cpfCnpj);
    }


    public Cliente(String email, String name, String cpfCnpj) {
        this.setEmail(email);
        this.setNome(name);
        this.setCpfCnpj(cpfCnpj);

    }


    public Cliente(String name, Long id) {
        this.setNome(name);
        this.setId(id);
    }




    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +

                ", cpfCnpj='" + cpfCnpj + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
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
}
