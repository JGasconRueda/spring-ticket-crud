package com.prueba.springticketcrud;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.repositories.TicketRepository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Stream;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    @DisplayName("Should Create Ticket")
    @Rollback(false)
    @Order(1)
    public void shouldCreateTicket(){

        Ticket ticket = new Ticket();
        ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ticket.setTotalAmount(20.0);

        Detail det1 = new Detail();
        det1.setDescription("Detail 1");
        det1.setAmount(10.0);

        Detail det2 = new Detail();
        det2.setDescription("Detail 2");
        det2.setAmount(5.0);

        Detail det3 = new Detail();
        det3.setDescription("Detail 3");
        det3.setAmount(3.0);

        ticket.addDetail(det1);
        ticket.addDetail(det2);
        ticket.addDetail(det3);

        Ticket savedTicket = ticketRepository.save(ticket);
        Assertions.assertNotNull(savedTicket);
        Assertions.assertFalse(savedTicket.getDetails().isEmpty());
    }

    @Test
    @DisplayName("Should Find By Id")
    @Order(2)
    public void shouldFindById() {
        
        Ticket findTicket = ticketRepository.findById(1L).orElse(null);
        Assertions.assertNotNull(findTicket);
    }
    
    @Test
    @DisplayName("Should Find All Tickets")
    @Order(3)
    public void shouldFindAllTickets() {
        
        Iterable<Ticket> findTickets = ticketRepository.findAll();
        Assertions.assertTrue(findTickets.spliterator().getExactSizeIfKnown() > 0);
    }
    
    @Test
    @DisplayName("Should Find By Creation Date Between")
    @Order(4)
    public void shouldFindByCreationDateBetween() {
        
    	Calendar startDate = new GregorianCalendar();
    	startDate.add(Calendar.HOUR, -1);
    	Calendar endDate = new GregorianCalendar();
    	endDate.add(Calendar.HOUR, 1);
    	
        List<Ticket> findTickets = ticketRepository.findByCreationDateBetween(new Timestamp(startDate.getTimeInMillis()),
        															new Timestamp(endDate.getTimeInMillis()));

        Assertions.assertFalse(findTickets.isEmpty());
    }
    
    @Test
    @DisplayName("Should Not Find By Creation Date Between")
    @Order(5)
    public void shouldNotFindByCreationDateBetween() {
        
    	Calendar startDate = new GregorianCalendar();
    	startDate.add(Calendar.HOUR, 1);
    	Calendar endDate = new GregorianCalendar();
    	endDate.add(Calendar.HOUR, 2);
    	
        List<Ticket> findTickets = ticketRepository.findByCreationDateBetween(new Timestamp(startDate.getTimeInMillis()),
        															new Timestamp(endDate.getTimeInMillis()));

        Assertions.assertTrue(findTickets.isEmpty());
    }
    
    @Test
    @DisplayName("Should Update Ticket")
    @Order(6)
    @Rollback(false)
    public void shouldUpdateTicket() {
    	
    	Ticket findTicket = ticketRepository.findById(1L).orElse(null);
        Double newAmount = 30.0;
        assert findTicket != null;
        findTicket.setTotalAmount(newAmount);
        ticketRepository.save(findTicket);
        
        Ticket updatedTicket = ticketRepository.findById(1L).orElse(null);

        assert updatedTicket != null;
        Assertions.assertEquals(updatedTicket.getTotalAmount(),newAmount);
    }
    
    @Test
    @DisplayName("Should Delete Ticket by Id")
    @Rollback(false)
    @Order(7)
    public void shouldDeleteTicketById() {
    	
    	boolean existBefore = ticketRepository.findById(1L).isPresent();
    	ticketRepository.deleteById(1L);
    	boolean existAfter = ticketRepository.findById(1L).isPresent();

        Assertions.assertTrue(existBefore);
        Assertions.assertFalse(existAfter);
    }
    
    @ParameterizedTest
    @DisplayName("Should Delete Ticket")
    @Rollback(false)
    @MethodSource("getTicketExample")
    @Order(8)
    public void shouldDeleteTicket(Ticket ticket) {
    	
    	Ticket savedTicket = ticketRepository.save(ticket);
    	boolean existBefore = ticketRepository.findById(savedTicket.getId()).isPresent();
    	ticketRepository.delete(savedTicket);
    	boolean existAfter = ticketRepository.findById(savedTicket.getId()).isPresent();

        Assertions.assertTrue(existBefore);
        Assertions.assertFalse(existAfter);
    	
    }

    private static Stream<Ticket> getTicketExample() {
        Ticket ticket = new Ticket();
        ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ticket.setTotalAmount(20.0);

        Detail det1 = new Detail();
        det1.setDescription("Detail 1");
        det1.setAmount(10.0);

        Detail det2 = new Detail();
        det2.setDescription("Detail 2");
        det2.setAmount(5.0);

        Detail det3 = new Detail();
        det3.setDescription("Detail 3");
        det3.setAmount(3.0);

        ticket.addDetail(det1);
        ticket.addDetail(det2);
        ticket.addDetail(det3);

        return Stream.of(ticket);
    }
}
