package com.prueba.springticketcrud.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.prueba.springticketcrud.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.prueba.springticketcrud.domain.Ticket;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
    private TicketService ticketService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.CREATED);
    }
		
    @GetMapping(value = "{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> getTicketById(@PathVariable long ticketId) {
        Optional<Ticket> ticket = ticketService.findById(ticketId);

        if(ticket.isPresent()) {
            return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }
    
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Ticket>> getAllTickets(@PathVariable Timestamp startDate, @PathVariable Timestamp endDate) {
//        return new ResponseEntity<>(ticketService.findByCreationDateBetween(startDate, endDate), HttpStatus.OK);
//    }
	
    @PutMapping(value = "{ticketId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
    	return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.OK);
    }
    
    @DeleteMapping(value = "{ticketId}")
    public void deleteBookById(@PathVariable long ticketId) {
    	ticketService.deleteById(ticketId);
    }
	
}
