package com.record.company.com.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "titleAlbum")
    private String titleAlbum;

    @Column(name= "numberSongs")
    private int numberSongs;

    @Column(name ="albumImg")
    private String AlbumImg;

    @Column(name = "publicationYear")
    private Date publicationYear;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private MusicGender musicGender;

    @ManyToMany(mappedBy = "album")
    private List <Purchase> purchase;

}
