package com.record.company.com.bussines;

import com.record.company.com.domain.dto.musicGenders.MusicGenderAlbumDto;
import com.record.company.com.domain.dto.musicGenders.CreateMusicGenderDto;
import com.record.company.com.domain.dto.musicGenders.MusicGenderDto;

import java.util.List;

public interface IMusicGenderServices {

    List<MusicGenderDto> getAllMusicGender ();

    MusicGenderDto getMusicGenderById (int id);

    MusicGenderDto createMusicGender (CreateMusicGenderDto musicGenres);

    MusicGenderDto updateMusicGender (int id, CreateMusicGenderDto musicGenres);

    MusicGenderDto deleteMusicGender (int id);


}
