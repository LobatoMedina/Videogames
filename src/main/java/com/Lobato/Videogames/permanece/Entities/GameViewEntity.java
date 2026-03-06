package com.Lobato.Videogames.permanece.Entities;


import jakarta.persistence.*;
@Entity
@Table(name ="vw_videogames")
public class GameViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer VideogameId;
    private String Videogame_videogame;
    private Integer esrb_id;
    private String images_url;
    private String Videogames_author;
    private Integer Videogames_stock;
    private String Videogames_Demo;

    public GameViewEntity() {
    }

    public GameViewEntity(Integer videogameId, String videogame_videogame, Integer esrb_id, String images_url, String videogames_author, Integer videogames_stock, String videogames_Demo) {
        VideogameId = videogameId;
        Videogame_videogame = videogame_videogame;
        this.esrb_id = esrb_id;
        this.images_url = images_url;
        Videogames_author = videogames_author;
        Videogames_stock = videogames_stock;
        Videogames_Demo = videogames_Demo;
    }

    public Integer getVideogameId() {
        return VideogameId;
    }

    public void setVideogameId(Integer videogameId) {
        VideogameId = videogameId;
    }

    public String getVideogame_videogame() {
        return Videogame_videogame;
    }

    public void setVideogame_videogame(String videogame_videogame) {
        Videogame_videogame = videogame_videogame;
    }

    public Integer getEsrb_id() {
        return esrb_id;
    }

    public void setEsrb_id(Integer esrb_id) {
        this.esrb_id = esrb_id;
    }

    public String getImages_url() {
        return images_url;
    }

    public void setImages_url(String images_url) {
        this.images_url = images_url;
    }

    public String getVideogames_author() {
        return Videogames_author;
    }

    public void setVideogames_author(String videogames_author) {
        Videogames_author = videogames_author;
    }

    public Integer getVideogames_stock() {
        return Videogames_stock;
    }

    public void setVideogames_stock(Integer videogames_stock) {
        Videogames_stock = videogames_stock;
    }

    public String getVideogames_Demo() {
        return Videogames_Demo;
    }

    public void setVideogames_Demo(String videogames_Demo) {
        Videogames_Demo = videogames_Demo;
    }
}
