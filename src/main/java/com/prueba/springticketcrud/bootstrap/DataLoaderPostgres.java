package com.prueba.springticketcrud.bootstrap;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.prueba.springticketcrud.domain.Detail;
import com.prueba.springticketcrud.domain.Ticket;
import com.prueba.springticketcrud.repositories.DetailRepository;
import com.prueba.springticketcrud.repositories.TicketRepository;

@Component
@Profile("default")
public class DataLoaderPostgres implements CommandLineRunner {
	
	private final DetailRepository detailRepository;
    private final TicketRepository ticketRepository;

    public DataLoaderPostgres(DetailRepository detailRepository, TicketRepository ticketRepository) {
        this.detailRepository = detailRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
    	if(ticketRepository.count() == 0L) {
    		loadData();
    	}
    	else {
    		System.out.println("Postgres DataLoaded...");
    	}
    }

    private void loadData() {

    	
        Ticket ticket = new Ticket(new Date(System.currentTimeMillis()),20.0);

        Detail det1 = new Detail(1,"Detail 1",10.0);
        Detail det2 = new Detail(1,"Detail 2",5.0);
        Detail det3 = new Detail(1,"Detail 3",3.0);

        ticket.addDetail(det1);
        ticket.addDetail(det2);
        ticket.addDetail(det3);
        ticketRepository.save(ticket);

        ticket = new Ticket(new Date(System.currentTimeMillis()),50.0);

        det1 = new Detail(2,"Detail 11",15.0);
        det2 = new Detail(2,"Detail 12",22.0);
        det3 = new Detail(2,"Detail 13",23.0);

        ticket.addDetail(det1);
        ticket.addDetail(det2);
        ticket.addDetail(det3);

        ticketRepository.save(ticket);

        System.out.println("Loaded Postgre Data...");
        
    }
}
