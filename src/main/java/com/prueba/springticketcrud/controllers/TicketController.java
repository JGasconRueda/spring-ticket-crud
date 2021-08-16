package com.prueba.springticketcrud.controllers;

import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.CREATED);
    }
		
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.findById(id);

        return ticket.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }
	
    @PutMapping(value = "/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
    	return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.OK);
    }
    
    @GetMapping(value = "/delete/{id}")
    public void deleteBookById(@PathVariable Long id) {
    	ticketService.deleteById(id);
    }
	
}
