package com.record.company.com.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "rol")
    private String rol;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "user_purchase",
    joinColumns = @JoinColumn(name = "id_user",
            referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_purchase",
    referencedColumnName = "id"))
    private List<Purchase> purchase;

}
