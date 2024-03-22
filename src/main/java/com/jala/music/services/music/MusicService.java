package com.jala.music.services.music;

import com.jala.music.entities.music.Music;
import com.jala.music.entities.music.dto.RequestMusicDto;
import com.jala.music.repositories.music.MusicRepository;
import com.jala.music.services.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private ArtistService artistService;

    public Music getMusic(UUID uuid) {
        if (musicRepository.findById(uuid).isPresent())
            return musicRepository.findById(uuid).get();
        return null;
    }

    public Music createMusic(RequestMusicDto musicDto) {
        var music = Music.builder()
                .title(musicDto.title())
                .duration(musicDto.duration())
                .genre(musicDto.musicGenre())
                .artist(artistService.getArtist(musicDto.artistUUID()))
                .build();
        return musicRepository.save(music);
    }

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }
}
