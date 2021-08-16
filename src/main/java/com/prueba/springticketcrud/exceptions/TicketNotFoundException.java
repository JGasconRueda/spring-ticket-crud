package com.prueba.springticketcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class TicketNotFoundException extends HttpStatusCodeException {
    private static final long serialVersionUID = 73263616501570402L;

    public TicketNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

    public TicketNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
