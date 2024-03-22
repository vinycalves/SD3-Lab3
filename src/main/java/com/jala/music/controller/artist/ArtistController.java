package com.jala.music.controller.artist;

import com.jala.music.entities.artist.Artist;
import com.jala.music.entities.artist.dto.RequestArtistDto;
import com.jala.music.entities.artist.dto.ResponseArtistDto;
import com.jala.music.services.artist.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ResponseArtistDto>> getAllArtists() {
        return ResponseEntity.status(HttpStatus.FOUND).body(artistService.getAllArtists());
    }

    @GetMapping("{artistID}")
    public ResponseEntity<ResponseArtistDto> readArtist(@PathVariable("artistID") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.getArtist(uuid));
    }

    @PostMapping
    public ResponseEntity<ResponseArtistDto> createArtist(@RequestBody @Valid RequestArtistDto artistDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.createArtist(artistDto));
    }

    @PostMapping(path = "/lot")
    public ResponseEntity<List<ResponseArtistDto>> createLotArtists(@RequestBody @Valid List<RequestArtistDto> artistDtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.createArtists(artistDtos));
    }

    @DeleteMapping("{artistID}")
    public ResponseEntity<String> deleteArtist(@PathVariable("artistID") UUID uuid) {
        if (artistService.deleteArtist(uuid))
            return ResponseEntity.ok("The data was deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The data was not found");
    }

    @PutMapping("{artistID}")
    public ResponseEntity<ResponseArtistDto> updateArtist(@PathVariable("artistID") UUID uuid, @RequestBody @Valid RequestArtistDto artistDto) {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.updateArtist(uuid, artistDto));
    }
}
