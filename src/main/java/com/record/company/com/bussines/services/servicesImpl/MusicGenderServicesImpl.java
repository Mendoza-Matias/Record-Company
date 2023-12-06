package com.record.company.com.bussines.services.servicesImpl;

import com.record.company.com.bussines.services.IMusicGenderServices;
import com.record.company.com.domain.dto.musicGenders.CreateMusicGenderDto;
import com.record.company.com.domain.dto.musicGenders.MusicGenderAlbumDto;
import com.record.company.com.domain.dto.musicGenders.MusicGenderDto;
import com.record.company.com.domain.entity.Album;
import com.record.company.com.domain.entity.MusicGender;
import com.record.company.com.exception.album.AlbumException;
import com.record.company.com.persistence.repository.IAlbumRepository;
import com.record.company.com.persistence.repository.IMusicGendersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MusicGenderServicesImpl implements IMusicGenderServices {

    @Autowired
    IMusicGendersRepository musicGendersRepository;

    @Autowired
    IAlbumRepository albumRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<MusicGenderDto> getAllMusicGender() {
        return musicGendersRepository.findAll().stream().map(gender -> modelMapper.map(gender, MusicGenderDto.class)).collect(Collectors.toList());
    }

    @Override
    public MusicGenderDto getMusicGenderById(int id) {
        return modelMapper.map(musicGendersRepository.findById(id), MusicGenderDto.class);
    }

    @Override
    public MusicGenderDto createMusicGender(int idAlbum, CreateMusicGenderDto musicGender) {

        MusicGender newMusicGender = new MusicGender();
        Album album = albumRepository.findById(idAlbum).orElseThrow(()-> new AlbumException("El id del album no existe"));

        newMusicGender.setNameGender(musicGender.getNameGender());
        newMusicGender.setAlbum(Collections.singletonList(album));

        return modelMapper.map(musicGendersRepository.save(newMusicGender), MusicGenderDto.class);
    }

    @Override
    public MusicGenderDto updateMusicGender(int id, int idAlbum , CreateMusicGenderDto musicGender) {

        Optional<MusicGender> musicGenderExist = musicGendersRepository.findById(id);
        Album album = albumRepository.findById(idAlbum).orElseThrow(()-> new AlbumException("El id del album no existe"));
        MusicGender newMusicGender = new MusicGender();

        if(musicGenderExist.isPresent()){
            newMusicGender = musicGenderExist.get();
            newMusicGender.setNameGender(musicGender.getNameGender());
            newMusicGender.setAlbum(Collections.singletonList(album));
        }

        return modelMapper.map(musicGendersRepository.save(newMusicGender), MusicGenderDto.class);
    }

    @Override
    public MusicGenderDto deleteMusicGender(int id) {

        Optional<MusicGender> musicGenderExist = musicGendersRepository.findById(id);
        MusicGender musicGender = new MusicGender();

        if(musicGenderExist.isPresent()){
            musicGender = musicGenderExist.get();
            musicGendersRepository.deleteById(id);
        }
        return modelMapper.map(musicGender, MusicGenderDto.class);
    }

    @Override
    public MusicGenderAlbumDto getAllAlbumByMusicGender(String musicGender) {
        return null;
    }
}
