package com.record.company.com.bussines;

import com.record.company.com.domain.dto.album.AlbumCreateDto;
import com.record.company.com.domain.dto.album.AlbumDto;

import java.util.List;

public interface IAlbumServices {


    List<AlbumDto> getAllAlbum();

    AlbumDto getAlbumById(int id);

    AlbumDto creteAlbum (int artistId,int musicGenderId ,AlbumCreateDto album);

    AlbumDto updateAlbum (int id , int artistId,int musicGenderId ,AlbumCreateDto album);

    AlbumDto deleteAlbum (int id);

}
