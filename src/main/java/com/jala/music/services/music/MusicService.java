package com.jala.music.services.music;

import com.jala.music.entities.music.Music;
import com.jala.music.entities.music.dto.RequestMusicDto;
import com.jala.music.repositories.music.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public Music getMusic(UUID uuid) {
        if (musicRepository.findById(uuid).isPresent())
            return musicRepository.findById(uuid).get();
        return null;
    }

    public Music createMusic(RequestMusicDto musicDto) {
        return Music.builder()
                .title(musicDto.title())
                .duration(musicDto.duration())
                .build();
    }
}
