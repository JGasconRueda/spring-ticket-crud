package com.prueba.springticketcrud.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "details")
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @NonNull
    private Date creationDate;

    @NonNull
    @Column(name = "total_amount")
    private Double totalAmount;

    @OneToMany(mappedBy ="ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Detail> details = new HashSet<>();

    public void addDetail(Detail detail){
        details.add(detail);
        detail.setTicket(this);
    }
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
