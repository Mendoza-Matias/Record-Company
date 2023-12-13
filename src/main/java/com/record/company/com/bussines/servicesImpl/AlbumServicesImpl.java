package com.record.company.com.bussines.servicesImpl;

import com.record.company.com.bussines.IAlbumServices;
import com.record.company.com.domain.dto.album.AlbumCreateDto;
import com.record.company.com.domain.dto.album.AlbumDto;
import com.record.company.com.domain.dto.album.AlbumInfoDto;
import com.record.company.com.domain.dto.album.AlbumTitleDto;
import com.record.company.com.domain.entity.Album;
import com.record.company.com.domain.entity.AlbumFile;
import com.record.company.com.domain.entity.Artist;
import com.record.company.com.domain.entity.MusicGender;
import com.record.company.com.exception.NotFoundException;
import com.record.company.com.persistence.repository.IAlbumFileRepository;
import com.record.company.com.persistence.repository.IAlbumRepository;
import com.record.company.com.persistence.repository.IArtistRepository;
import com.record.company.com.persistence.repository.IMusicGendersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServicesImpl implements IAlbumServices {

    @Autowired
    IAlbumRepository albumRepository;

    @Autowired
    IArtistRepository artistRepository;

    @Autowired
    IMusicGendersRepository musicGendersRepository;

    @Autowired
    IAlbumFileRepository albumFileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<AlbumDto> getAllAlbum() {
        return albumRepository.findAll().stream().map(album -> modelMapper.map(album,AlbumDto.class)).collect(Collectors.toList());
    }
    @Override
    public AlbumDto getAlbumById(int id) {
        Album albumExist = albumRepository.findById(id).orElseThrow(() -> new NotFoundException("Id no encontrado"));
        return modelMapper.map(albumExist,AlbumDto.class);
    }
    @Override
    public List<AlbumTitleDto> getAllAlbumByNameArtist(String artist) {
        return albumRepository.getAllAlbumByNameArtist(artist).stream().map(album -> modelMapper.map(album,AlbumTitleDto.class)).collect(Collectors.toList());
    }
    @Override
    public List<AlbumInfoDto> getAllAlbumByMusicGenres(String musicGender) {
        return albumRepository.getAllAlbumByMusicGenres(musicGender).stream().map(albumInfoDto -> modelMapper.map(albumInfoDto,AlbumInfoDto.class)).collect(Collectors.toList());
    }
    @Override
    public AlbumDto creteAlbum(int artistId,int musicGenderId,int albumFileId ,AlbumCreateDto album) {

        Artist artist = artistRepository.findById(artistId).orElseThrow(()-> new NotFoundException("Id del artista no encontrado"));
        MusicGender gender = musicGendersRepository.findById(musicGenderId).orElseThrow(()-> new NotFoundException("Id del genero musical no encontrado"));
        AlbumFile albumFile = albumFileRepository.findById(albumFileId).orElseThrow(()-> new NotFoundException("Id de imagen no encontrada"));

        Album newAlbum = new Album();
        newAlbum.setTitleAlbum(album.getTitleAlbum());
        newAlbum.setNumberSongs(album.getNumberSongs());
        newAlbum.setPublicationYear(album.getPublicationYear());
        newAlbum.setAlbumFile(albumFile);
        newAlbum.setArtist(artist);
        newAlbum.setMusicGender(gender);


        return modelMapper.map(albumRepository.save(newAlbum),AlbumDto.class);
    }
    @Override
    public AlbumDto updateAlbum (int id , int artistId , int musicGenderId , int albumFileId ,AlbumCreateDto album) {
        Optional <Album> albumExist = albumRepository.findById(id);
        Artist artist = artistRepository.findById(artistId).orElseThrow(()-> new NotFoundException("Id del artista no encontrado"));
        MusicGender gender = musicGendersRepository.findById(musicGenderId).orElseThrow(()-> new NotFoundException("Id del genero musical no encontrado"));
        AlbumFile albumFile = albumFileRepository.findById(albumFileId).orElseThrow(()-> new NotFoundException("Id de imagen no encontrada"));

        Album newAlbum = new Album();

        if(albumExist.isPresent()){
                newAlbum = albumExist.get();
                newAlbum.setTitleAlbum(album.getTitleAlbum());
                newAlbum.setNumberSongs(album.getNumberSongs());
                newAlbum.setPublicationYear(album.getPublicationYear());
                newAlbum.setAlbumFile(albumFile);
                newAlbum.setMusicGender(gender);
                newAlbum.setArtist(artist);
        }

        return modelMapper.map(albumRepository.save(newAlbum),AlbumDto.class);
    }
    @Override
    public AlbumDto deleteAlbum(int id) {

        Optional <Album> album = albumRepository.findById(id);
        Album albumDelete = new Album();

        if(album.isPresent()){
            albumDelete = album.get();
            albumRepository.deleteById(id);
        }

        return modelMapper.map(albumDelete,AlbumDto.class);
    }

}
