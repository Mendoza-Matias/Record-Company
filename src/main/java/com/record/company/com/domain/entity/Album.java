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
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;

    @Column(name = "album_title")
    private String titleAlbum;

    @Column(name= "number_songs")
    private int numberSongs;

    @OneToOne
    @JoinColumn(name = "id_albumFile")
    private AlbumFile albumFile;

    @Column(name = "year_publication")
    private Date publicationYear;

    @ManyToOne
    @JoinColumn(name = "id_gender")
    private MusicGender musicGender;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "album_purchase",joinColumns =
            @JoinColumn(name = "id_album",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_purchase",
                    referencedColumnName = "id")
    )
    private List <Purchase> purchase;

}
