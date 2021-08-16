package com.prueba.springticketcrud.repositories;

import com.prueba.springticketcrud.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    List<Ticket> findByCreationDateBetween(Date startDate, Date endDate);

}
