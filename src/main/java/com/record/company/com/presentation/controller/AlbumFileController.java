package com.record.company.com.presentation.controller;

import com.record.company.com.bussines.IAlbumFileServices;
import com.record.company.com.domain.dto.albumFile.AlbumFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/albumFile")
public class AlbumFileController {

    @Autowired
    IAlbumFileServices albumFileServices;

    @PostMapping
    public ResponseEntity<AlbumFileDto> createAlbumFile (@RequestParam("image") MultipartFile file){
        return  ResponseEntity.created(URI.create("/api/v1/albumFile")).body(albumFileServices.creteAlbumFile(file));
    }
}
