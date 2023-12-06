package com.record.company.com.presentation.controller.album;

import com.record.company.com.bussines.services.IAlbumServices;
import com.record.company.com.domain.dto.album.AlbumCreateDto;
import com.record.company.com.domain.dto.album.AlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recordCompany/album")
public class AlbumController {

    @Autowired
    IAlbumServices albumServices;

    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAllAlbum(){
        return ResponseEntity.ok(albumServices.getAllAlbum());
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDto> getAlbumById (@PathVariable("id") int id){
          return new ResponseEntity<>(albumServices.getAlbumById(id), HttpStatus.OK);
       };

    @PostMapping
    public ResponseEntity<AlbumDto> createAlbum(@RequestBody AlbumCreateDto album){
        return ResponseEntity.created(URI.create("/api/V1/recordCompany/album")).body(albumServices.creteAlbum(album));
    };

    @PutMapping("{id}")
    public ResponseEntity<AlbumDto> updateAlbum (@PathVariable("id") int id , @RequestBody AlbumCreateDto album){
        return ResponseEntity.ok(albumServices.updateAlbum(id,album));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AlbumDto> deleteAlbum (@PathVariable("id") int id){
        return ResponseEntity.ok(albumServices.deleteAlbum(id));
    }

}
