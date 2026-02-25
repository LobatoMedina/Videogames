package com.Lobato.Videogames.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cat_esrb")
public class EsrbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
