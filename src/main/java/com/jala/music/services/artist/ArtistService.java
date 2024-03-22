package com.jala.music.services.artist;

import com.jala.music.entities.artist.Artist;
import com.jala.music.entities.artist.dto.RequestArtistDto;
import com.jala.music.repositories.artist.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public Artist getArtist(UUID uuid) {
        if (artistRepository.findById(uuid).isPresent()) return artistRepository.findById(uuid).get();
        return null;
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist createArtist(RequestArtistDto artistDto) {
        var artist = Artist.builder().name(artistDto.name()).birthdate(artistDto.birthdate()).build();
        return artistRepository.save(artist);
    }

    public List<Artist> createArtists(List<RequestArtistDto> artistsDto) {
        var artists = artistsDto.stream().map(artistDto -> Artist.builder().name(artistDto.name()).birthdate(artistDto.birthdate()).build()).toList();
        return artistRepository.saveAll(artists);
    }

    public boolean deleteArtist(UUID uuid) {
        if (artistRepository.findById(uuid).isPresent()) {
            artistRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    @Transactional
    public Artist updateArtist(UUID artistUUID, RequestArtistDto artistDto) {
        var artist = artistRepository.findById(artistUUID).orElseThrow(() -> new IllegalStateException("This artist ID" + artistUUID + "doesn't exist!"));

        if (!Objects.equals(artist.getName(), artistDto.name())) {
            artist.setName(artistDto.name());
        }
        if (!Objects.equals(artist.getBirthdate(), artistDto.birthdate())) {
            artist.setBirthdate(artistDto.birthdate());
        }
        return artist;
    }
}
