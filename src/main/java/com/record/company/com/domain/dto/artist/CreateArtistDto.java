package com.record.company.com.domain.dto.artist;

import com.record.company.com.domain.entity.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateArtistDto {

    private String nameArtist;

    private String country;

}
