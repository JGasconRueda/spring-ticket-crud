package com.prueba.springticketcrud.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "ticket")
@Entity
@Table(name = "details")
public class Detail extends BaseEntity{

    @NonNull
    @Column(name = "line_id")
    private Integer lineID;

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Ticket ticket;

}
