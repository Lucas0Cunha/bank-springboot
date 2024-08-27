package com.bank.bank.infra;

import com.bank.bank.exceptions.ArgumentoMissedException;
import com.bank.bank.exceptions.ListaVaziaException;
import com.bank.bank.exceptions.ValorNaoExisteException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArgumentoMissedException.class)
    public ResponseEntity<String> handleIllegalArgumentException(ArgumentoMissedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Ocorreu um erro interno", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValorNaoExisteException.class)
    public ResponseEntity<String> handleNotFoundException(ValorNaoExisteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> sqlError(DataAccessException e){
        return new ResponseEntity<>("Erro em banco de dados",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ListaVaziaException.class)
    public ResponseEntity<String>handleListEmpty(ListaVaziaException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }


}
