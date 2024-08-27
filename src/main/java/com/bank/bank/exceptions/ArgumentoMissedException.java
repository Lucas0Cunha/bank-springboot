package com.bank.bank.exceptions;

public class ArgumentoMissedException extends IllegalArgumentException{

    public ArgumentoMissedException (){
        super("Argumento precisa ser preenchido");
    }

    public ArgumentoMissedException(String message){
        super(message);
    }
}
