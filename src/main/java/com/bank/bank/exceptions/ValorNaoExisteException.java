package com.bank.bank.exceptions;

public class ValorNaoExisteException extends RuntimeException{

    public ValorNaoExisteException(){
        super("Cliente inexistente");
    }

    public ValorNaoExisteException(String message){
        super(message);
    }

}
