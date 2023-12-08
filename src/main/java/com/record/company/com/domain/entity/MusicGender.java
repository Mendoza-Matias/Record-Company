package com.record.company.com.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "music_genres")
public class MusicGender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gender_name")
    private String nameGender;

    @OneToMany(mappedBy = "musicGender" , fetch = FetchType.LAZY)
    private List <Album> album;
}
