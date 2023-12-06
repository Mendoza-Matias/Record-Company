package com.record.company.com.presentation.controller.musicGender;

import com.record.company.com.bussines.services.IMusicGenderServices;
import com.record.company.com.domain.dto.musicGenders.CreateMusicGenderDto;
import com.record.company.com.domain.dto.musicGenders.MusicGenderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recordCompany/musicGender")
public class MusicGenderController {

    @Autowired
    IMusicGenderServices musicGenderServices;

    @GetMapping
    public ResponseEntity<List <MusicGenderDto>> getAllMusicGender(){
        return ResponseEntity.ok(musicGenderServices.getAllMusicGender());
    }

    @GetMapping("{id}")
    public ResponseEntity<MusicGenderDto> getMusicGenderById(@PathVariable("id") int id){
        return ResponseEntity.ok(musicGenderServices.getMusicGenderById(id));
    }

    @PostMapping("{idAlbum}")
    public ResponseEntity<MusicGenderDto> createMusicGender(@PathVariable("idAlbum") int idAlbum , @RequestBody CreateMusicGenderDto musicGender){
        return ResponseEntity.created(URI.create("/api/v1/recordCompany/musicGender")).body(musicGenderServices.createMusicGender(idAlbum,musicGender));
    }

    @PutMapping("{id}/{idAlbum}")
    public ResponseEntity<MusicGenderDto> updateMusicGender(@PathVariable("id") int id, @PathVariable("idAlbum") int idAlbum, @RequestBody CreateMusicGenderDto musicGender){
        return ResponseEntity.ok(musicGenderServices.updateMusicGender(id,idAlbum,musicGender));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<MusicGenderDto> deleteMusicGender(@PathVariable("id") int id){
        return ResponseEntity.ok(musicGenderServices.deleteMusicGender(id));
    }

}
