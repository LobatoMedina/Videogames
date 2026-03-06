package com.Lobato.Videogames.permanece.DTOs;

import java.util.List;

public class DTOVideogame {
    private Integer Id;
    private String name;
    private Integer esrbid;
    private String imgUrl;
    public String author;
    private String specs;
    private double price;
    private List<GenreDTO> genres;
    private List<PlatformDTO> platforms;
    private Integer stock;
    private String demo;

    public DTOVideogame(Integer id, String name, Integer esrbid, String imgUrl, String author, String specs, double price, List<GenreDTO> genres, List<PlatformDTO> platforms, Integer stock, String demo) {
        Id = id;
        this.name = name;
        this.esrbid = esrbid;
        this.imgUrl = imgUrl;
        this.author = author;
        this.specs = specs;
        this.price = price;
        this.genres = genres;
        this.platforms = platforms;
        this.stock = stock;
        this.demo = demo;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEsrbid() {
        return esrbid;
    }

    public void setEsrbid(Integer esrbid) {
        this.esrbid = esrbid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public List<PlatformDTO> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformDTO> platforms) {
        this.platforms = platforms;
    }
}