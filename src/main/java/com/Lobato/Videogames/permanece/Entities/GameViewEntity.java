package com.Lobato.Videogames.permanece.Entities;


import jakarta.persistence.*;
@Entity
@Table(name ="vw_videogames")
public class GameViewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String title;
    private Integer esrb;
    private String image_url;
    private String author;
    private String specs;
    private double price;
    private Integer Videogame_stock;
    private String Videogame_Demo;

    public GameViewEntity() {
    }

    public GameViewEntity(Integer id, String title, Integer esrb, String image_url, String author, String specs, double price, Integer videogame_stock, String videogame_Demo) {
        Id = id;
        this.title = title;
        this.esrb = esrb;
        this.image_url = image_url;
        this.author = author;
        this.specs = specs;
        this.price = price;
        Videogame_stock = videogame_stock;
        Videogame_Demo = videogame_Demo;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEsrb() {
        return esrb;
    }

    public void setEsrb(Integer esrb) {
        this.esrb = esrb;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getVideogame_stock() {
        return Videogame_stock;
    }

    public void setVideogame_stock(Integer videogame_stock) {
        Videogame_stock = videogame_stock;
    }

    public String getVideogame_Demo() {
        return Videogame_Demo;
    }

    public void setVideogame_Demo(String videogame_Demo) {
        Videogame_Demo = videogame_Demo;
    }
}
