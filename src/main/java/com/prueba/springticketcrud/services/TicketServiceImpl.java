package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.repositories.DetailRepository;
import com.prueba.springticketcrud.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final DetailRepository detailRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, DetailRepository detailRepository) {
        this.ticketRepository = ticketRepository;
        this.detailRepository = detailRepository;
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    @Override
    public Optional<Ticket> findById(Long aLong) {
        return ticketRepository.findById(aLong);
    }

    @Override
    public Ticket save(Ticket ticket) {
    	validateTicket(ticket);
        return ticketRepository.save(ticket);
    }

    private void validateTicket(Ticket ticket) {
		ticket.validateCreationDate();
		ticket.validateTotalAmount();
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
    public List<Ticket> findByCreationDateBetween(Date startDate, Date endDate) {
        return ticketRepository.findByCreationDateBetween(startDate,endDate);
    }
}
