package com.prueba.springticketcrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.springticketcrud.controllers.TicketController;
import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.services.TicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    TicketService ticketService;

    @Test
    @DisplayName("Should Create Ticket")
    public void shouldCreateTicket() throws Exception{

        Ticket ticket = new Ticket(new Date(System.currentTimeMillis()),20.0);

        Detail det1 = new Detail(1,"Detail 1",10.0);
        Detail det2 = new Detail(1,"Detail 2",5.0);
        Detail det3 = new Detail(1,"Detail 3",3.0);

        ticket.addDetail(det1);
        ticket.addDetail(det2);
        ticket.addDetail(det3);

        when(ticketService.save(ticket)).thenReturn(ticket);

        this.mockMvc
                .perform(post("/tickets/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(ticket)))
                    .andExpect(status().isCreated())
                    .andExpect(content().string(objectMapper.writeValueAsString(ticket)));
    }

    @Test
    @DisplayName("Should Find By Id")
    public void shouldFindById() throws Exception{

        Ticket ticket = new Ticket(new Date(System.currentTimeMillis()),20.0);

        Detail det1 = new Detail(1,"Detail 1",10.0);
        Detail det2 = new Detail(1,"Detail 2",5.0);
        Detail det3 = new Detail(1,"Detail 3",3.0);

        ticket.addDetail(det1);
        ticket.addDetail(det2);
        ticket.addDetail(det3);
        ticket.setId(1L);

        when(ticketService.findById(1L)).thenReturn(Optional.of(ticket));

        this.mockMvc
                .perform(get("/tickets/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id",is(1)));

    }

    @Test
    @DisplayName("Should Not a Ticket Find By Id and should return Not Gound")
    public void shouldNotFindByIdAndReturn404() throws Exception {

        when(ticketService.findById(1L)).thenReturn(Optional.empty());

        this.mockMvc
                .perform(get("/tickets/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should Find All Tickets")
    public void shouldFindAllTickets() throws Exception{

        when(ticketService.findAll()).thenReturn(createTicketListOf2Tickets());

        this.mockMvc
                .perform(get("/tickets/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$",hasSize(2)));

    }

    @Test
    @DisplayName("Should Update Ticket")
    public void shouldUpdateTicket() throws Exception{

        Ticket ticket = new Ticket(new Date(System.currentTimeMillis()),20.0);

        Detail det1 = new Detail(1,"Detail 1",10.0);
        det1.setId(1L);
        Detail det2 = new Detail(1,"Detail 2",5.0);
        det2.setId(2L);
        Detail det3 = new Detail(1,"Detail 3",3.0);
        det3.setId(3L);

        ticket.addDetail(det1);
        ticket.addDetail(det2);
        ticket.addDetail(det3);
        ticket.setId(1L);

        when(ticketService.update(1L,ticket)).thenReturn(ticket);

        this.mockMvc
                .perform(put("/tickets/update/ticket/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(objectMapper.writeValueAsString(ticket)));

    }

    @Test
    @DisplayName("Should Delete Ticket by Id")
    public void shouldDeleteTicketById() throws Exception{

        TicketService  serviceSpy = spy(ticketService);
        doNothing().when(serviceSpy).deleteById(1L);

        this.mockMvc
                .perform(delete("/tickets/1")
                    .contentType("application/json"))
                .andExpect(status().isNoContent());
        verify(ticketService, times(1)).deleteById(1L);

    }

    private Ticket createTicket(Date creationDate, Double totalAmount, List<Detail> details){
        Ticket ticket = new Ticket();
        ticket.setCreationDate(creationDate);
        ticket.setTotalAmount(totalAmount);
        details.forEach(ticket::addDetail);

        return ticket;
    }

    private List<Ticket> createTicketListOf2Tickets(){
        Detail det1 = new Detail(1,"Detail 1",10.0);
        Detail det2 = new Detail(1,"Detail 2",5.0);
        Detail det3 = new Detail(1,"Detail 3",3.0);
        List<Detail> details1 = new ArrayList<>();
        details1.add(det1);
        details1.add(det2);
        details1.add(det3);

        Ticket ticket1 = createTicket(new Date(System.currentTimeMillis()),200.0,details1);

        Detail det12 = new Detail(2,"Detail 21",50.0);
        Detail det22 = new Detail(2,"Detail 22",65.0);
        Detail det32 = new Detail(2,"Detail 23",38.0);
        List<Detail> details2 = new ArrayList<>();
        details2.add(det12);
        details2.add(det22);
        details2.add(det32);

        Ticket ticket2 = createTicket(new Date(System.currentTimeMillis()),150.0,details2);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);

        return tickets;
    }
}
