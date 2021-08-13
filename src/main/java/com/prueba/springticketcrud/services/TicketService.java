package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Ticket;

import java.sql.Timestamp;
import java.util.Set;

public interface TicketService extends CrudService<Ticket, Long> {
    Set<Ticket> findByCreationDateBetween(Timestamp startDate, Timestamp endDate);
}
