package com.record.company.com.presentation.controller;

import com.record.company.com.bussines.IAlbumServices;
import com.record.company.com.domain.dto.album.AlbumCreateDto;
import com.record.company.com.domain.dto.album.AlbumDto;
import com.record.company.com.domain.dto.album.AlbumInfoDto;
import com.record.company.com.domain.dto.album.AlbumTitleDto;
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

    @GetMapping("/artist")
    public ResponseEntity<List<AlbumTitleDto>> getAllAlbumByNameArtist(@RequestParam("name") String artist){
        return ResponseEntity.ok(albumServices.getAllAlbumByNameArtist(artist));
    }

    @GetMapping("/genres")
    public ResponseEntity<List<AlbumInfoDto>> getAllAlbumByMusicGenres (@RequestParam("name") String musicGender) {
        return ResponseEntity.ok(albumServices.getAllAlbumByMusicGenres(musicGender));
    }

    @PostMapping("{artistId}/{musicGenderId}/{albumFileId}")
    public ResponseEntity<AlbumDto> createAlbum
            (@PathVariable("artistId") int artistId ,@PathVariable("musicGenderId") int musicGenderId ,@PathVariable("albumFileId") int albumFileId,@RequestBody AlbumCreateDto album){
        return ResponseEntity.created(URI.create("/api/V1/recordCompany/album")).body(albumServices.creteAlbum(artistId,musicGenderId,albumFileId,album));
    };

    @PutMapping("{id}/{artistId}/{musicGenderId}/{albumFileId}")
    public ResponseEntity<AlbumDto> updateAlbum (@PathVariable("id") int id ,
                                                 @PathVariable("artistId") int artistId ,
                                                 @PathVariable("musicGenderId") int musicGenderId ,
                                                 @PathVariable("albumFileId") int albumFileId,
                                                 @RequestBody AlbumCreateDto album){
        return ResponseEntity.ok(albumServices.updateAlbum(id,artistId,musicGenderId,albumFileId,album));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<AlbumDto> deleteAlbum (@PathVariable("id") int id){
        return ResponseEntity.ok(albumServices.deleteAlbum(id));
    }

}
