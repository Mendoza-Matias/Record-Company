package com.record.company.com.domain.dto.album;

import com.record.company.com.domain.entity.Artist;
import com.record.company.com.domain.entity.MusicGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumCreateDto {

    private String titleAlbum;

    private int numberSongs;

    private Date publicationYear;

}
