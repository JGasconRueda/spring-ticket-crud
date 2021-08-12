package com.prueba.springticketcrud;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.repositories.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class TicketTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    @DisplayName("Should Create Ticket")
    public void shouldCreateTicket(){
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

        ticketRepository.save(ticket);
    }

    @Test
    @DisplayName("Should Not Create Ticket when creation Date is null")
    public void shouldThrowRuntimeExceptionWhenCreationDateIsNull(){

    }
}
