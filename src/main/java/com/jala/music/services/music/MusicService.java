package com.jala.music.services.music;

import com.jala.music.entities.music.Music;
import com.jala.music.entities.music.MusicGenre;
import com.jala.music.entities.music.dto.RequestMusicDto;
import com.jala.music.entities.music.dto.ResponseMusicDto;
import com.jala.music.repositories.artist.ArtistRepository;
import com.jala.music.repositories.music.MusicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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
        var music = Music.builder().title(musicDto.title()).duration(musicDto.duration()).genre(musicDto.musicGenre()).artist(artistRepository.findById(musicDto.artistUUID()).get()).build();
        var savedMusic = musicRepository.save(music);
        return new ResponseMusicDto(savedMusic.getTitle(), savedMusic.getGenre(), savedMusic.getDuration(), savedMusic.getArtist().getUuid());
    }

    public List<ResponseMusicDto> getAllMusics() {
        return musicRepository.findAll().stream().map(music -> new ResponseMusicDto(music.getTitle(), music.getGenre(), music.getDuration(), music.getArtist().getUuid())).toList();
    }

    @Transactional
    public ResponseMusicDto updateMusic(UUID musicUUID, RequestMusicDto musicDto) {
        var music = musicRepository.findById(musicUUID).orElseThrow(() -> new IllegalStateException("This artist ID" + musicUUID + "doesn't exist!"));

        if (!Objects.equals(music.getTitle(), musicDto.title())) {
            music.setTitle(musicDto.title());
        }
        if (!Objects.equals(music.getGenre(), musicDto.musicGenre())) {
            music.setGenre(musicDto.musicGenre());
        }
        if (!Objects.equals(music.getDuration(), musicDto.duration())) {
            music.setDuration(musicDto.duration());
        }
        if (!Objects.equals(music.getArtist().getUuid(), musicDto.artistUUID())) {
            music.setArtist(artistRepository.findById(musicDto.artistUUID()).get());
        }
        return ResponseMusicDto.builder().title(music.getTitle()).musicGenre(music.getGenre()).duration(musicDto.duration()).artistUUID(musicDto.artistUUID()).build();
    }

    public boolean deleteMusic(UUID uuid) {
        if (musicRepository.findById(uuid).isPresent()) {
            musicRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    public List<ResponseMusicDto> getAllMusicsByFilter(String title, BigDecimal duration, MusicGenre musicGenre, UUID artistUUID) {
        var musics = musicRepository.findByAttributes(title, musicGenre, duration, artistUUID);
        return musics.stream().map(music -> ResponseMusicDto.builder().title(music.getTitle()).musicGenre(music.getGenre()).duration(music.getDuration()).artistUUID(music.getArtist().getUuid()).build()).toList();
    }
}
