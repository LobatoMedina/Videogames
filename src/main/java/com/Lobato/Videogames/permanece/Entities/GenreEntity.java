package com.Lobato.Videogames.permanece.Entities;


import com.Lobato.Videogames.permanece.DTOs.GenreDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cat_genres")
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
