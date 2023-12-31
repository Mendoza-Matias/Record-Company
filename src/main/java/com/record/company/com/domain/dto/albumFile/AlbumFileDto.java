package com.record.company.com.domain.dto.albumFile;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumFileDto {

    private Integer id;

    private  String nameImg;

    private String path;

    private String type;

}
