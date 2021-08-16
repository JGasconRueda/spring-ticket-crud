package com.prueba.springticketcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class DetailNotFoundException extends HttpStatusCodeException {
    public DetailNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public DetailNotFoundException(String s) {
        super(HttpStatus.NOT_FOUND,s);
    }
}
