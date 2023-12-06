package com.record.company.com.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_booking")
    private String purchaseCode;

    @Column(name = "date_booking")
    private LocalDate dateBooking;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToMany
    private List <Album> album;


}
