package com.Lobato.Videogames.permanece.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cat_esrb")
public class EsrbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int limitAge;

}
