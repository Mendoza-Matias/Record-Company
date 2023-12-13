package com.record.company.com.bussines.servicesImpl;

import com.record.company.com.bussines.IArtistServices;
import com.record.company.com.domain.dto.artist.ArtistAlbumDto;
import com.record.company.com.domain.dto.artist.ArtistDto;
import com.record.company.com.domain.dto.artist.ArtistNameDto;
import com.record.company.com.domain.dto.artist.CreateArtistDto;
import com.record.company.com.domain.entity.Album;
import com.record.company.com.domain.entity.Artist;
import com.record.company.com.exception.NotFoundException;
import com.record.company.com.persistence.repository.IAlbumRepository;
import com.record.company.com.persistence.repository.IArtistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArtisServicesImpl implements IArtistServices {
    @Autowired
    IArtistRepository artistRepository;

    @Autowired
    IAlbumRepository albumRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ArtistDto> getAllArtist() {
        return artistRepository.findAll().stream().map(artist -> modelMapper.map(artist,ArtistDto.class)).collect(Collectors.toList());
    }

    @Override
    public ArtistDto getArtistById(int id) {
        return modelMapper.map(artistRepository.findById(id),ArtistDto.class);
    }

    @Override
    public List<ArtistNameDto> getAllArtistByCountry(String country) {
        return artistRepository.getAllArtistByCountry(country).stream().map(artist -> modelMapper.map(artist,ArtistNameDto.class)).collect(Collectors.toList());
    }

    @Override
    public ArtistDto createArtist(CreateArtistDto artist) {
        Artist newArtist = new Artist();

        newArtist.setNameArtist(artist.getNameArtist());
        newArtist.setCountry(artist.getCountry());

        return modelMapper.map(artistRepository.save(newArtist),ArtistDto.class) ;
    }

    @Override
    public ArtistDto updateArtist(int id , CreateArtistDto artist) {

        Optional <Artist> artistExist = artistRepository.findById(id);
        Artist newArtist = new Artist();

        if (artistExist.isPresent()){
            newArtist = artistExist.get();
            newArtist.setNameArtist(artist.getNameArtist());
            newArtist.setCountry(artist.getCountry());
        }
        return modelMapper.map(artistRepository.save(newArtist),ArtistDto.class);
    }

    @Override
    public ArtistDto deleteArtist(int id) {

        Optional<Artist> artistExist = artistRepository.findById(id);
        Artist artist = new Artist();

        if(artistExist.isPresent()){
            artist = artistExist.get();
            artistRepository.deleteById(id);
        }
        return modelMapper.map(artist,ArtistDto.class);
    }

}
