package com.jala.music.entities.music.dto;

import com.jala.music.entities.music.MusicGenre;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ResponseMusicDto(@NotNull String title, @NotNull MusicGenre musicGenre, @Positive BigDecimal duration, @NotNull UUID artistUUID) {
}
