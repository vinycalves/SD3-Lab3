package com.jala.music.entities.music.dto;

import com.jala.music.entities.music.MusicGenre;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateRequestMusicDto(@NotNull String title, @NotNull MusicGenre musicGenre, BigDecimal duration, UUID artistId) {
}
