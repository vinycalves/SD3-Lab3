package com.jala.music.services.artist;

import com.jala.music.entities.artist.Artist;
import com.jala.music.entities.artist.dto.RequestArtistDto;
import com.jala.music.repositories.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistService {
    @Autowired
     private ArtistRepository artistRepository;

    public Artist getArtist(UUID uuid) {
        if (artistRepository.findById(uuid).isPresent())
            return artistRepository.findById(uuid).get();
        return null;
    }

    public Artist createArtist(RequestArtistDto artistDto) {
        var artist = Artist.builder()
                .name(artistDto.name())
                .birthdate(artistDto.birthdate())
                .build();
        return artistRepository.save(artist);
    }
}
