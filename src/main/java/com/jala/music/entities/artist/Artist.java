package com.jala.music.entities.artist;

import com.jala.music.entities.AbstractEntity;
import com.jala.music.entities.music.Music;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ARTISTS")
public class Artist extends AbstractEntity {
    @Column(name = "NM_ARTIST")
    private String name;
    @Column(name = "DT_BIRTH_DATE")
    private Date birthdate;
    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Music> musics;
}
