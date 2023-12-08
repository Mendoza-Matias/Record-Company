package com.record.company.com.presentation.controller;

import com.record.company.com.bussines.IAlbumServices;
import com.record.company.com.domain.dto.album.AlbumCreateDto;
import com.record.company.com.domain.dto.album.AlbumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/album")
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

    @PostMapping("{artistId}/{musicGenderId}")
    public ResponseEntity<AlbumDto> createAlbum
            (@PathVariable("artistId") int artistId ,@PathVariable("musicGenderId") int musicGenderId ,@RequestBody AlbumCreateDto album){
        return ResponseEntity.created(URI.create("/api/V1/recordCompany/album")).body(albumServices.creteAlbum(artistId,musicGenderId,album));
    };

    @PutMapping("{id}/{artistId}/{musicGenderId}")
    public ResponseEntity<AlbumDto> updateAlbum (@PathVariable("id") int id ,
                                                 @PathVariable("artistId") int artistId ,
                                                 @PathVariable("musicGenderId") int musicGenderId ,
                                                 @RequestBody AlbumCreateDto album){
        return ResponseEntity.ok(albumServices.updateAlbum(id,artistId,musicGenderId,album));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AlbumDto> deleteAlbum (@PathVariable("id") int id){
        return ResponseEntity.ok(albumServices.deleteAlbum(id));
    }

}
