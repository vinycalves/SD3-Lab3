package com.jala.music.services.artist;

import com.jala.music.repositories.artist.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
     private ArtistRepository artistRepository;


}
