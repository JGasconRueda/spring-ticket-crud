package com.prueba.springticketcrud.controllers;

import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
@Tag(name = "tickets", description = "API Rest CRUD for Tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Create e new Ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket created",
                                content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = Ticket.class))})})
    @PostMapping(value = "/save")
    public ResponseEntity<Ticket> addTicket(@Parameter(description = "Ticket to be created")
                                                @RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketService.save(ticket), HttpStatus.CREATED);
    }

    @Operation(summary = "Get a Ticket by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Ticket",
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = Ticket.class)) }
                        ),
            @ApiResponse(responseCode = "404", description = "Ticket not found",
                        content = @Content)
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ticket> getTicketById(@Parameter(description = "Id of Ticket to be searched")
                                                    @PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.findById(id);

        return ticket.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all Tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the Tickets",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ticket.class)) }
            )
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Update a Ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ticket.class)) }
            )
    })
    @PutMapping(value = "/update/ticket/{id}")
    public ResponseEntity<Ticket> updateTicket(@Parameter(description = "Id of Ticket to be updated")
                                                   @PathVariable Long id,
                                               @Parameter(description = "Ticket to be updated")
                                               @RequestBody Ticket ticket) {
    	return new ResponseEntity<>(ticketService.update(id,ticket), HttpStatus.OK);
    }

    @Operation(summary = "Delete a Ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ticket deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ticket.class)) }
            )
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteTicketById(@Parameter(description = "Id of Ticket to be deleted")
                                                     @PathVariable Long id) {
    	ticketService.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
	
}
