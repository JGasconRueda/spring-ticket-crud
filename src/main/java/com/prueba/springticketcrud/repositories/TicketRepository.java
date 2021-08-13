package com.prueba.springticketcrud.repositories;

import com.prueba.springticketcrud.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findByCreationDateBetween(Timestamp startDate, Timestamp endDate);

}
