package com.Lobato.Videogames.permanece.Entities;


import com.Lobato.Videogames.permanece.DTOs.GenreDTO;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.query.Procedure;

@Entity
@Table(name = "tbl_cat_genres")
@NamedStoredProcedureQuery(name = "get_genres", procedureName = "sp_findAllGenres_FromVideogame", parameters = {
        @StoredProcedureParameter(name = "_gameId",mode = ParameterMode.IN, type = Integer.class)

}, resultClasses = GenreEntity.class)
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GenreId")
    private Integer id;
    @Column(name = "Genre_genre")
    private String genre;

    public GenreEntity() {
    }

    public GenreEntity(Integer id, String genre) {
        this.id = id;
        this.genre = genre;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
