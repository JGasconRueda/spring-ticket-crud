package com.prueba.springticketcrud.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "details")
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="ticket")
    private Set<Detail> details = new HashSet<>();

    public void validateCreationDate(){
        if(this.getCreationDate()==null){
            throw new RuntimeException("Creation Date Cannot be null");
        }
    }

    public void validateTotalAmount(){
        if(this.getTotalAmount()==null){
            throw new RuntimeException("Total Amount Cannot be null");
        }
    }
}
