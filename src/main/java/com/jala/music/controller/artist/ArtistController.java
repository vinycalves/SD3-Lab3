package com.jala.music.controller.artist;

import com.jala.music.entities.artist.Artist;
import com.jala.music.entities.artist.dto.RequestArtistDto;
import com.jala.music.services.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(name = "/api/v1/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("{artistID}")
    public ResponseEntity<Artist> getArtist(@PathVariable("artistID") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.getArtist(uuid));
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody RequestArtistDto artistDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.createArtist(artistDto));
    }
}
