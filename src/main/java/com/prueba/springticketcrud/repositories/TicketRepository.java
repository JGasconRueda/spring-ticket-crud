package com.prueba.springticketcrud.repositories;

import com.prueba.springticketcrud.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Set;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    Set<Ticket> findByCreationDateBetween(Timestamp startDate, Timestamp endDate);

}
