package com.prueba.springticketcrud;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.services.TicketService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;


@DataJpaTest
@TestMethodOrder
public class TicketTest {

    @Autowired
    private TicketService ticketService;

    @Test
    @DisplayName("Should Create Ticket")
    @Rollback(false)
    @MethodSource("getTicketExample")
    @Order(1)
    public void shouldCreateTicket(Ticket ticket){
        
        Ticket savedTicket = ticketService.save(ticket);
        assertNotNull(savedTicket);
        assertFalse(savedTicket.getDetails().isEmpty());
    }

    @Test
    @DisplayName("Should Not Create Ticket when creation Date is null")
    @Order(2)
    public void shouldThrowRuntimeExceptionWhenCreationDateIsNull(){
    	Ticket ticket = new Ticket();
        ticket.setTotalAmount(20.0);

        Detail det1 = new Detail();
        det1.setTicket(ticket);
        det1.setDescription("Detail 1");
        det1.setAmount(10.0);

        Set<Detail> detailSet = new HashSet<>();
        detailSet.add(det1);
 
        ticket.setDetails(detailSet);

        Assertions.assertThrows(RuntimeException.class, () -> {
        	ticketService.save(ticket);
        });
    }
    
    @Test
    @DisplayName("Should Not Create Ticket when Total Amount is null")
    @Order(3)
    public void shouldThrowRuntimeExceptionWhenTotalAmountIsNull(){
    	Ticket ticket = new Ticket();
        ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
        
        Detail det1 = new Detail();
        det1.setTicket(ticket);
        det1.setDescription("Detail 1");
        det1.setAmount(10.0);

        Set<Detail> detailSet = new HashSet<>();
        detailSet.add(det1);
 
        ticket.setDetails(detailSet);

        Assertions.assertThrows(RuntimeException.class, () -> {
        	ticketService.save(ticket);
        });
    }
    
    @Test
    @DisplayName("Should Not Create Ticket when Description of Detail is null")
    @Order(4)
    public void shouldThrowRuntimeExceptionWhenDescriptionIsNull(){
    	Ticket ticket = new Ticket();
        ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ticket.setTotalAmount(20.0);

        Detail det1 = new Detail();
        det1.setTicket(ticket);
        det1.setAmount(10.0);

        Set<Detail> detailSet = new HashSet<>();
        detailSet.add(det1);
 
        ticket.setDetails(detailSet);

        Assertions.assertThrows(RuntimeException.class, () -> {
        	ticketService.save(ticket);
        });
    }
    
    @Test
    @DisplayName("Should Not Create Ticket when Amount of Detail is null")
    @Order(5)
    public void shouldThrowRuntimeExceptionWhenAmountIsNull(){
    	Ticket ticket = new Ticket();
        ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ticket.setTotalAmount(20.0);

        Detail det1 = new Detail();
        det1.setTicket(ticket);
        det1.setDescription("Detail 1");

        Set<Detail> detailSet = new HashSet<>();
        detailSet.add(det1);
 
        ticket.setDetails(detailSet);

        Assertions.assertThrows(RuntimeException.class, () -> {
        	ticketService.save(ticket);
        });
    }
    
    @Test
    @DisplayName("Should Find By Id")
    @Order(6)
    public void shouldFindById(Ticket ticket) {
        
        Ticket findTicket = ticketService.findById(1L);
        
        assertNotNull(findTicket);
    }
    
    @Test
    @DisplayName("Should Find All Tickets")
    @Order(7)
    public void shouldFindAllTickets() {
        
        List<Ticket> findTickets = ticketService.findAll();
        
        assertFalse(findTickets.isEmpty());
    }
    
    @Test
    @DisplayName("Should Find By Creation Date Between")
    @Order(8)
    public void shouldFindByCreationDateBetween() {
        
    	Calendar startDate = new GregorianCalendar();
    	startDate.add(Calendar.HOUR, -1);
    	Calendar endDate = new GregorianCalendar();
    	endDate.add(Calendar.HOUR, 1);
    	
        Set<Ticket> findTickets = ticketService.findByCreationDateBetween(new Timestamp(startDate.getTimeInMillis()), 
        															new Timestamp(endDate.getTimeInMillis()));
        
        assertFalse(findTickets.isEmpty());
    }
    
    @Test
    @DisplayName("Should Not Find By Creation Date Between")
    @Order(9)
    public void shouldFindByCreationDateBetween() {
        
    	Calendar startDate = new GregorianCalendar();
    	startDate.add(Calendar.HOUR, 1);
    	Calendar endDate = new GregorianCalendar();
    	endDate.add(Calendar.HOUR, 2);
    	
        Set<Ticket> findTickets = ticketService.findByCreationDateBetween(new Timestamp(startDate.getTimeInMillis()), 
        															new Timestamp(endDate.getTimeInMillis()));
        
        assertTrue(findTickets.isEmpty());
    }
    
    @Test
    @DisplayName("Should Update Ticket")
    @Order(10)
    @Rollback(false)
    public void shouldUpdateTicket() {
    	
    	Ticket findTicket = ticketService.findById(1L);
        Double newAmount = 30.0;
        findTicket.setTotalAmount(newAmount);
        ticketService.save(ticket);
        
        Ticket updatedTicket = ticketService.findById(1L);
        		
        assertThat(updatedTicket.getTotalAmount()).isEqualTo(newAmount);
    }
    
    @Test
    @DisplayName("Should Delete Ticket by Id")
    @Rollback(false)
    @Order(11)
    public void shouldDeleteTicketById() {
    	
    	boolean existBefore = ticketService.findById(1L).isPresent();
    	ticketService.deleteById(1);
    	boolean existAfter = ticketService.findById(1L).isPresent();
    	
    	assertTrue(existBefore);
    	assertFalse(existAfter);
    }
    
    @Test
    @DisplayName("Should Delete Ticket")
    @Rollback(false)
    @MethodSource("getTicketExample")
    @Order(12)
    public void shouldDeleteTicket(Ticket ticket) {
    	
    	Ticket savedTicket = ticketService.save(ticket);
    	boolean existBefore = ticketService.findById(savedTicket.getId()).isPresent();
    	ticketService.delete(savedTicket);
    	boolean existAfter = ticketService.findById(savedTicket.getId()).isPresent();
    	
    	assertTrue(existBefore);
    	assertFalse(existAfter);
    	
    }
    
    private Ticket getTicketExample() {
    	Ticket ticket = new Ticket();
        ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ticket.setTotalAmount(20.0);

        Detail det1 = new Detail();
        det1.setTicket(ticket);
        det1.setDescription("Detail 1");
        det1.setAmount(10.0);

        Detail det2 = new Detail();
        det2.setTicket(ticket);
        det2.setDescription("Detail 2");
        det2.setAmount(5.0);

        Detail det3 = new Detail();
        det3.setTicket(ticket);
        det3.setDescription("Detail 3");
        det3.setAmount(3.0);

        Set<Detail> detailSet = new HashSet<>();
        detailSet.add(det1);
        detailSet.add(det2);
        detailSet.add(det3);

        ticket.setDetails(detailSet);
    }
    
}
