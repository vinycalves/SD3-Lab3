package com.jala.music.services.artist;

import com.jala.music.entities.artist.Artist;
import com.jala.music.entities.artist.dto.RequestArtistDto;
import com.jala.music.entities.artist.dto.ResponseArtistDto;
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

    public ResponseArtistDto getArtist(UUID uuid) {
        if (artistRepository.findById(uuid).isPresent()) {
            var artist = artistRepository.findById(uuid).get();
            return ResponseArtistDto.builder().name(artist.getName()).birthdate(artist.getBirthdate()).build();
        }
        return null;
    }

    public List<ResponseArtistDto> getAllArtists() {
        return artistRepository.findAll().stream().map(artist -> ResponseArtistDto.builder().name(artist.getName()).birthdate(artist.getBirthdate()).build()).toList();
    }

    public ResponseArtistDto createArtist(RequestArtistDto artistDto) {
        var artist = Artist.builder().name(artistDto.name()).birthdate(artistDto.birthdate()).build();
        var savedArtist = artistRepository.save(artist);
        return ResponseArtistDto.builder().name(savedArtist.getName()).birthdate(savedArtist.getBirthdate()).build();
    }

    public List<ResponseArtistDto> createArtists(List<RequestArtistDto> artistsDto) {
        var artists = artistsDto.stream().map(artistDto -> Artist.builder().name(artistDto.name()).birthdate(artistDto.birthdate()).build()).toList();
        var artistsSaved = artistRepository.saveAll(artists);
        return artistsSaved.stream().map(artist -> ResponseArtistDto.builder().name(artist.getName()).birthdate(artist.getBirthdate()).build()).toList();
    }

    public boolean deleteArtist(UUID uuid) {
        if (artistRepository.findById(uuid).isPresent()) {
            artistRepository.deleteById(uuid);
            return true;
        }
        return false;
    }

    @Transactional
    public ResponseArtistDto updateArtist(UUID artistUUID, RequestArtistDto artistDto) {
        var artist = artistRepository.findById(artistUUID).orElseThrow(() -> new IllegalStateException("This artist ID" + artistUUID + "doesn't exist!"));

        if (!Objects.equals(artist.getName(), artistDto.name())) {
            artist.setName(artistDto.name());
        }
        if (!Objects.equals(artist.getBirthdate(), artistDto.birthdate())) {
            artist.setBirthdate(artistDto.birthdate());
        }
        return ResponseArtistDto.builder().name(artist.getName()).birthdate(artist.getBirthdate()).build();
    }
}
