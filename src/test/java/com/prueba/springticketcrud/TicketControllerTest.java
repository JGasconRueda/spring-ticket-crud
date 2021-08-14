package com.prueba.springticketcrud;

import com.prueba.springticketcrud.controllers.TicketController;
import com.prueba.springticketcrud.services.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Test
    public void shouldFindAllTickets() {

    }
}
