package com.jala.music.services.music;

import com.jala.music.entities.music.Music;
import com.jala.music.entities.music.dto.RequestMusicDto;
import com.jala.music.entities.music.dto.ResponseMusicDto;
import com.jala.music.repositories.artist.ArtistRepository;
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
    private ArtistRepository artistRepository;

    public ResponseMusicDto getMusic(UUID uuid) {
        if (musicRepository.findById(uuid).isPresent()) {
            var music = musicRepository.findById(uuid).get();
            return new ResponseMusicDto(music.getTitle(), music.getGenre(), music.getDuration(), music.getArtist().getUuid());
        }
        return null;
    }

    public ResponseMusicDto createMusic(RequestMusicDto musicDto) {
        var music = Music.builder()
                .title(musicDto.title())
                .duration(musicDto.duration())
                .genre(musicDto.musicGenre())
                .artist(artistRepository.findById(musicDto.artistUUID()).get())
                .build();
        var savedMusic = musicRepository.save(music);
        return new ResponseMusicDto(savedMusic.getTitle(), savedMusic.getGenre(), savedMusic.getDuration(), savedMusic.getArtist().getUuid());
    }

    public List<ResponseMusicDto> getAllMusics() {
        return musicRepository.findAll().stream().map(music -> new ResponseMusicDto(music.getTitle(), music.getGenre(), music.getDuration(), music.getArtist().getUuid())).toList();
    }
}
