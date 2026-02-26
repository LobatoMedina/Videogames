package com.Lobato.Videogames.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cat_genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}
