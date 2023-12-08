package com.record.company.com.domain.dto.album;

import com.record.company.com.domain.dto.artist.ArtistNameDto;
import com.record.company.com.domain.dto.musicGenders.MusicGenderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumInfoDto {

    private ArtistNameDto artist;

    private String titleAlbum;

    private MusicGenderDto musicGender;
}
