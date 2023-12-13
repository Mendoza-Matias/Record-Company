package com.record.company.com.presentation.controller;

import com.record.company.com.bussines.IAlbumFileServices;
import com.record.company.com.domain.dto.albumFile.AlbumFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/albumFile")
public class AlbumFileController {

    @Autowired
    IAlbumFileServices albumFileServices;

    @GetMapping
    public ResponseEntity<List<AlbumFileDto>> getAllFileAlbum(){
        return ResponseEntity.ok(albumFileServices.getAllAlbumFile());
    }

    @GetMapping("{id}")
    public ResponseEntity<Resource> getAlbumFileById(@PathVariable("id") int id) throws MalformedURLException {
        AlbumFileDto albumFileDto = albumFileServices.getFileById(id);
        Resource resource = albumFileServices.getAlbumFileById(id);
        HttpHeaders headers = new HttpHeaders();
        String fileExtension = StringUtils.getFilenameExtension(albumFileDto.getNameImg());
        MediaType mediaType = MediaType.parseMediaType("image/" + fileExtension.toLowerCase());
        headers.setContentType(mediaType);
        return new ResponseEntity<>(resource,headers,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumFileDto> createAlbumFile (@RequestParam("image") MultipartFile file){
        return  ResponseEntity.created(URI.create("/api/v1/albumFile")).body(albumFileServices.creteAlbumFile(file));
    }

    @PutMapping("{id}")
    public ResponseEntity<AlbumFileDto> uptadateAlbumFile(@PathVariable("id") int id , @RequestParam("image") MultipartFile file){
        return ResponseEntity.ok(albumFileServices.updateAlbum(id,file));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AlbumFileDto> deleteAlbumFile(@PathVariable("id") int id){
        return ResponseEntity.ok(albumFileServices.deleteAlbum(id));
    }
}
