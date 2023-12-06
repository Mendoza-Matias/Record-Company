package com.record.company.com.bussines.services.servicesImpl;

import com.record.company.com.bussines.services.IAlbumServices;
import com.record.company.com.domain.dto.album.AlbumCreateDto;
import com.record.company.com.domain.dto.album.AlbumDto;
import com.record.company.com.domain.dto.album.AlbumMusicGenderDto;
import com.record.company.com.domain.entity.Album;
import com.record.company.com.exception.album.AlbumException;
import com.record.company.com.persistence.repository.IAlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServicesImpl implements IAlbumServices {

    @Autowired
    IAlbumRepository albumRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<AlbumDto> getAllAlbum() {
        return albumRepository.findAll().stream().map(album -> modelMapper.map(album,AlbumDto.class)).collect(Collectors.toList());
    }
    @Override
    public AlbumDto getAlbumById(int id) {
        Album albumExist = albumRepository.findById(id).orElseThrow(() -> new AlbumException("Id no encontrado"));
        return modelMapper.map(albumExist,AlbumDto.class);
    }
    @Override
    public AlbumDto creteAlbum(AlbumCreateDto albumCreateDto) {

        Album album = new Album();
        album.setTitleAlbum(albumCreateDto.getTitleAlbum());
        album.setNumberSongs(albumCreateDto.getNumberSongs());
        album.setPublicationYear(albumCreateDto.getPublicationYear());


        return modelMapper.map(albumRepository.save(album),AlbumDto.class);
    }
    @Override
    public AlbumDto updateAlbum(int id, AlbumCreateDto album) {

        Optional <Album> albumExist = albumRepository.findById(id);
        Album newAlbum = new Album();

        if(albumExist.isPresent()){
                newAlbum = albumExist.get();
                newAlbum.setTitleAlbum(album.getTitleAlbum());
                newAlbum.setNumberSongs(album.getNumberSongs());
                newAlbum.setPublicationYear(album.getPublicationYear());
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
