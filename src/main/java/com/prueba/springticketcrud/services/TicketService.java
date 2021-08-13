package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Ticket;

import java.sql.Timestamp;
import java.util.List;

public interface TicketService extends CrudService<Ticket, Long> {
    List<Ticket> findByCreationDateBetween(Timestamp startDate, Timestamp endDate);
}
