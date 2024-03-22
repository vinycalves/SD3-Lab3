package com.jala.music.repositories.music;

import com.jala.music.entities.artist.Artist;
import com.jala.music.entities.music.Music;
import com.jala.music.entities.music.MusicGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface MusicRepository extends JpaRepository<Music, UUID> {
    @Query("SELECT p FROM Music p WHERE " +
            "(:title IS NULL OR p.title = :title) AND " +
            "(:genre IS NULL OR p.genre = :genre) AND " +
            "(:duration IS NULL OR p.duration = :duration) AND " +
            "(:artist IS NULL OR p.artist = :artist)")
    List<Music> findByAttributes(@Param("title") String title, @Param("genre") MusicGenre genre,
                                   @Param("duration") BigDecimal duration, @Param("artist") UUID artist);
}
