package com.bank.bank.dto;

// O RequestDTO neste caso foi criado como record para criar um constructor que dirá que a requisição deve ou retornar ou demandar os parametros nele inseridos
// como é um Request ent provavelmente ele demandará que passem os valores dos respectivos parametros
// Record- uma forma de declarar a classe quando o objetivo é ter valores IMUTAVEIS, que n podem ser alterados apos a criação da classe, por exemplo:
// Não posso adicionar parametros a esta classe sem ser através dessa classe.
public record ClienteRequestDTO(String nome,String email,String cpfCnpj) {}
