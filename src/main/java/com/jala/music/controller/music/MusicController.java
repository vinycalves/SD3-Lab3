package com.jala.music.controller.music;

import com.jala.music.entities.artist.Artist;
import com.jala.music.entities.music.MusicGenre;
import com.jala.music.entities.music.dto.RequestMusicDto;
import com.jala.music.entities.music.dto.ResponseMusicDto;
import com.jala.music.services.music.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping
    public ResponseEntity<List<ResponseMusicDto>> getAllMusics() {
        return ResponseEntity.status(HttpStatus.FOUND).body(musicService.getAllMusics());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ResponseMusicDto>> getAllMusicsByFilter(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) BigDecimal duration,
            @RequestParam(required = false) MusicGenre musicGenre,
            @RequestParam(required = false) UUID artistUUID
    ) {
        return ResponseEntity.status(HttpStatus.FOUND).body(musicService.getAllMusicsByFilter(title,duration,musicGenre,artistUUID));
    }

    @GetMapping("{musicID}")
    public ResponseEntity<ResponseMusicDto> getMusic(@PathVariable("musicID") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.getMusic(uuid));
    }

    @PostMapping
    public ResponseEntity<ResponseMusicDto> createMusic(@RequestBody @Valid RequestMusicDto musicDto) {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.createMusic(musicDto));
    }

    @PutMapping("{musicID}")
    public ResponseEntity<ResponseMusicDto> updateMusic(@PathVariable("musicID") UUID musicUUID, @RequestBody @Valid RequestMusicDto requestMusicDto) {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.updateMusic(musicUUID, requestMusicDto));
    }

    @DeleteMapping("{musicID}")
    public ResponseEntity<String> deleteMusic(@PathVariable("musicID") UUID uuid) {
        if (musicService.deleteMusic(uuid))
            return ResponseEntity.ok("The data was deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The data was not found");
    }
}
