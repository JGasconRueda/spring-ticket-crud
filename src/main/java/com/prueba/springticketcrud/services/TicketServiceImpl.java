package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.repositories.DetailRepository;
import com.prueba.springticketcrud.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DetailRepository detailRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, DetailRepository detailRepository) {
        this.ticketRepository = ticketRepository;
        this.detailRepository = detailRepository;
    }

    @Override
    public Set<Ticket> findAll() {
        Set<Ticket> tickets = new HashSet<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    @Override
    public Ticket findById(Long aLong) {
        return ticketRepository.findById(aLong).orElse(null);
    }

    @Override
    public Ticket save(Ticket ticket) {
    	validateTicket(ticket);
        return ticketRepository.save(ticket);
    }

    private void validateTicket(Ticket ticket) {
		ticket.validateCreationDate();
		ticket.validateAmount();
	}

	@Override
    public void delete(Ticket object) {
        ticketRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ticketRepository.deleteById(aLong);
    }

    @Override
    public Iterable<Ticket> findByCreationDateBetween(Timestamp startDate, Timestamp endDate) {
        return ticketRepository.findByCreationDateBetween(startDate,endDate);
    }
}
