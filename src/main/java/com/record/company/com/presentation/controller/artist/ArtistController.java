package com.record.company.com.presentation.controller.artist;

import com.record.company.com.bussines.services.IArtistServices;
import com.record.company.com.domain.dto.artist.ArtistDto;
import com.record.company.com.domain.dto.artist.CreateArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recordCompany/artist")
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

    @PostMapping("{idAlbum}")
    public ResponseEntity<ArtistDto> createArtist (@PathVariable("idAlbum") int idAlbum,@RequestBody CreateArtistDto artist){
        return ResponseEntity.created(URI.create("/api/v1/recordCompany/artist")).body(artistServices.createArtist(idAlbum,artist));
    }

    @PutMapping("{id}/{idAlbum}")
    public ResponseEntity<ArtistDto> updateArtist(@PathVariable("id") int id,@PathVariable("idAlbum") int idAlbum, @RequestBody CreateArtistDto artist){
        return ResponseEntity.ok(artistServices.updateArtist(id,idAlbum,artist));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ArtistDto> deleteArtist(@PathVariable("id") int id){
        return ResponseEntity.ok(artistServices.deleteArtist(id));
    }
}
