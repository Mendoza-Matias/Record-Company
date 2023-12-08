package com.record.company.com.presentation.controller;

import com.record.company.com.bussines.IArtistServices;
import com.record.company.com.domain.dto.artist.ArtistDto;
import com.record.company.com.domain.dto.artist.CreateArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    @Autowired
    IArtistServices artistServices;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtist(){
        return ResponseEntity.ok(artistServices.getAllArtist());
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable("id") int id ){
        return ResponseEntity.ok(artistServices.getArtistById(id));
    }

    @PostMapping("{albumId}")
    public ResponseEntity<ArtistDto> createArtist (@PathVariable("albumId") int albumId ,@RequestBody CreateArtistDto artist){
        return ResponseEntity.created(URI.create("/api/v1/recordCompany/artist")).body(artistServices.createArtist(albumId,artist));
    }

    @PutMapping("{id}/{albumId}")
    public ResponseEntity<ArtistDto> updateArtist( @PathVariable("id") int id , @PathVariable("albumId") int albumId , @RequestBody CreateArtistDto artist){
        return ResponseEntity.ok(artistServices.updateArtist(id,albumId,artist));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ArtistDto> deleteArtist(@PathVariable("id") int id){
        return ResponseEntity.ok(artistServices.deleteArtist(id));
    }
}
