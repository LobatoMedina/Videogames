package com.Lobato.Videogames.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cat_platforms")
public class PlatformEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer platformId;
    private String platform_platform;
}
