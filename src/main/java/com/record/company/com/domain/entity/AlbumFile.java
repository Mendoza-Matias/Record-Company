package com.record.company.com.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="album_file")
public class AlbumFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_img")
    private  String nameImg;

    @Column(name = "type")
    private String type;

    @Column(name = "file_path")
    private String path;

}
