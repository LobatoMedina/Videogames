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
    private Integer Videogames_stock;
    private String Videogames_Demo;

    public GameViewEntity() {
    }

    public GameViewEntity(Integer id, String title, Integer esrb, String image_url, String author, String specs, double price, Integer videogames_stock, String videogames_Demo) {
        Id = id;
        this.title = title;
        this.esrb = esrb;
        this.image_url = image_url;
        this.author = author;
        this.specs = specs;
        this.price = price;
        Videogames_stock = videogames_stock;
        Videogames_Demo = videogames_Demo;
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
