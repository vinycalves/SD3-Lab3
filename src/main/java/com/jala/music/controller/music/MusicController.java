package com.jala.music.controller.music;

import com.jala.music.entities.music.Music;
import com.jala.music.entities.music.dto.RequestMusicDto;
import com.jala.music.entities.music.dto.ResponseMusicDto;
import com.jala.music.services.music.MusicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping
    public ResponseEntity<List<Music>> getAllMusics() {
        return ResponseEntity.status(HttpStatus.FOUND).body(musicService.getAllMusics());
    }

    @GetMapping("{musicID}")
    public ResponseEntity<Music> getMusic(@PathVariable("musicID") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.getMusic(uuid));
    }

    @PostMapping
    public ResponseEntity<ResponseMusicDto> createMusic(@RequestBody @Valid RequestMusicDto musicDto) {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.createMusic(musicDto));
    }
}
