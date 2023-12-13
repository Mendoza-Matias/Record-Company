package com.record.company.com.bussines;

import com.record.company.com.domain.dto.albumFile.AlbumFileDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;

public interface IAlbumFileServices {

    List<AlbumFileDto> getAllAlbumFile();

    AlbumFileDto getFileById(int id);

    Resource getAlbumFileById(int id) throws MalformedURLException;


    AlbumFileDto creteAlbumFile (MultipartFile file);

    AlbumFileDto updateAlbum (int id , MultipartFile file);

    AlbumFileDto deleteAlbum (int id);

}
