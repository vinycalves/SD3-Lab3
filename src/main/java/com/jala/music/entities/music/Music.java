package com.jala.music.entities.music;

import com.jala.music.entities.AbstractEntity;
import com.jala.music.entities.artist.Artist;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_MUSICS")
public class Music extends AbstractEntity {
    @Column(name = "NM_TITLE")
    private String title;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "DS_GENRE")
    private MusicGenre genre;
    @Column(name = "NM_DURATION", precision = 5, scale = 2)
    private BigDecimal duration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTIST_ID")
    private Artist artist;
}
