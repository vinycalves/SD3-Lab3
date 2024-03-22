package com.jala.music.entities.artist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;

import java.util.Date;

@Builder
public record ResponseArtistDto(@NotNull String name, @Past Date birthdate) {
}
