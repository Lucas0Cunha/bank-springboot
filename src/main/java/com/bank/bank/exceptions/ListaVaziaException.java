package com.bank.bank.exceptions;

public class ListaVaziaException extends RuntimeException{


    public ListaVaziaException (){
        super("A lista não possui dados");
    }

    public ListaVaziaException(String message){
        super(message);
    }
}
