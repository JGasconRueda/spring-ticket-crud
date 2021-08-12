package com.prueba.springticketcrud.bootstrap;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.repositories.DetailRepository;
import com.prueba.springticketcrud.repositories.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final DetailRepository detailRepository;
    private final TicketRepository ticketRepository;

    public DataLoader(DetailRepository detailRepository, TicketRepository ticketRepository) {
        this.detailRepository = detailRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Ticket ticket = new Ticket();
        ticket.setCreationDate(new Timestamp(System.currentTimeMillis()));
        ticket.setTotalAmount(20.0);

        Detail det1 = new Detail();
        det1.setTicket(ticket);
        det1.setDescription("Detail 1");
        det1.setAmount(10.0);
        ticket.getDetails().add(det1);

        Detail det2 = new Detail();
        det2.setTicket(ticket);
        det2.setDescription("Detail 2");
        det2.setAmount(5.0);
        ticket.getDetails().add(det2);

        Detail det3 = new Detail();
        det3.setTicket(ticket);
        det3.setDescription("Detail 3");
        det3.setAmount(3.0);
        ticket.getDetails().add(det3);

        ticketRepository.save(ticket);

        System.out.println("Loaded Data...");
    }
}
