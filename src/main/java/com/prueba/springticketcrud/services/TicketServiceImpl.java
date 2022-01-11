package com.prueba.springticketcrud.services;

import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.exceptions.TicketNotFoundException;
import com.prueba.springticketcrud.repositories.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        logger.info("TicketServiceImpl - findAll: Return "+tickets.size()+" tickets");
        return tickets;
    }

    @Override
    public Optional<Ticket> findById(Long aLong) {
        logger.info("TicketServiceImpl - findById: Ticket id "+ aLong);
        return ticketRepository.findById(aLong);
    }

    @Override
    public Ticket save(Ticket ticket) {
        logger.info("TicketServiceImpl - save: "+ticket.toString());
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Long aLong, Ticket object) {
        logger.info("TicketServiceImpl - update: TicketId "+aLong);
        Optional<Ticket> ticket = ticketRepository.findById(aLong);

        if(!ticket.isPresent()){
            logger.error("TicketServiceImpl - update: TicketNotFound");
            throw new TicketNotFoundException("Ticket with id "+aLong+" not found!!");
        }

        Ticket original = ticket.get();

        BeanUtils.copyProperties(object,original);

        return ticketRepository.save(original);
    }

    @Override
    public void delete(Ticket object) {
        logger.info("TicketServiceImpl - delete: "+object.toString());
        ticketRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        logger.info("TicketServiceImpl - deleteById: TicketId "+aLong);
        ticketRepository.deleteById(aLong);
    }

    @Override
    public List<Ticket> findByCreationDateBetween(Date startDate, Date endDate) {
        return ticketRepository.findByCreationDateBetween(startDate,endDate);
    }
}
