package br.com.workforall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(String message){
        super(message);
    }
}