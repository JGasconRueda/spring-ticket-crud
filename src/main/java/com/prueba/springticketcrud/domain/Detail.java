package com.prueba.springticketcrud.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "ticket")
@Entity
@Table(name = "details")
public class Detail extends BaseEntity{

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "ticked_id")
    private Ticket ticket;

}
