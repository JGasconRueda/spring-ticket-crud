package com.prueba.springticketcrud.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Ticket ticket;

}
