package com.jala.music.entities.artist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.util.Date;

public record RequestArtistDto(@NotNull String name, @Past Date birthdate) {
}
