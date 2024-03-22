package com.jala.music.entities.artist.dto;

import com.jala.music.entities.music.Music;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;
import java.util.Set;

public record UpdateArtistWithMusicDto(@NotNull String name, @Past Date birthdate, Set<Music> music) {
}
