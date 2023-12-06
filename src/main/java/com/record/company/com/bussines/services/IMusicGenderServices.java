package com.record.company.com.bussines.services;

import com.record.company.com.domain.dto.musicGenders.MusicGenderAlbumDto;
import com.record.company.com.domain.dto.musicGenders.CreateMusicGenderDto;
import com.record.company.com.domain.dto.musicGenders.MusicGenderDto;

import java.util.List;

public interface IMusicGenderServices {

    List<MusicGenderDto> getAllMusicGender ();

    MusicGenderDto getMusicGenderById (int id);

    MusicGenderDto createMusicGender (int idAlbum, CreateMusicGenderDto musicGender);

    MusicGenderDto updateMusicGender (int id, int idAlbum, CreateMusicGenderDto musicGender);

    MusicGenderDto deleteMusicGender (int id);

     MusicGenderAlbumDto getAllAlbumByMusicGender(String musicGender);

}
