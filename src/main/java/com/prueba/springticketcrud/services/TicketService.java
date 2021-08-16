package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketService extends CrudService<Ticket, Long> {
    List<Ticket> findByCreationDateBetween(Date startDate, Date endDate);
}
