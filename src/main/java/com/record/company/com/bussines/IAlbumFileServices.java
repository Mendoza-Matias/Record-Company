package com.record.company.com.bussines;

import com.record.company.com.domain.dto.album.AlbumCreateDto;
import com.record.company.com.domain.dto.album.AlbumDto;
import com.record.company.com.domain.dto.albumFile.AlbumFileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAlbumFileServices {

    List<AlbumFileDto> getAllAlbumFile();

    AlbumFileDto getAlbumFileById(int id);

    AlbumFileDto creteAlbumFile (MultipartFile file);

    AlbumFileDto updateAlbum (int id , MultipartFile file);

    AlbumFileDto deleteAlbum (int id);

}
