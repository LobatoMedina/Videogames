package com.Lobato.Videogames.permanece.Infraestructure;

import com.Lobato.Videogames.permanece.DTOs.GenreDTO;
import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface  GenreRepository extends JpaRepository<GenreEntity, Integer> {
    @Procedure(name = "get_genres")
    public List<GenreEntity> getAllGenresFromGame(@Param("_gameId") Integer id);
}
