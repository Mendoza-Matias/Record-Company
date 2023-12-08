package com.record.company.com.bussines;

import com.record.company.com.domain.dto.artist.ArtistDto;
import com.record.company.com.domain.dto.artist.CreateArtistDto;

import java.util.List;

public interface IArtistServices {

    List <ArtistDto> getAllArtist ();

    ArtistDto getArtistById (int id);

    ArtistDto createArtist(int albumId , CreateArtistDto artist);

    ArtistDto updateArtist(int id , int albumId , CreateArtistDto artist);

    ArtistDto deleteArtist(int id);




}
