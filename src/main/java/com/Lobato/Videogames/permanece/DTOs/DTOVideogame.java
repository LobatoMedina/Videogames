package com.Lobato.Videogames.permanece.DTOs;

import java.util.List;

public class DTOVideogame {

    private String name;
    private Integer esrbid;
    private String imgUrl;
    public String author;
    private String specs;
    private double price;
    private List<Integer> genres;
    private List<Integer> platforms;

    public DTOVideogame(String name, Integer esrbid, String imgUrl, String author, String specs, double price, List<Integer> genres, List<Integer> platforms) {
        this.name = name;
        this.esrbid = esrbid;
        this.imgUrl = imgUrl;
        this.author = author;
        this.specs = specs;
        this.price = price;
        this.genres = genres;
        this.platforms = platforms;
    }

    public void setEsrbid(Integer esrbid) {
        this.esrbid = esrbid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEsrbid() {
        return esrbid;
    }

    public void setEsrbid(int esrbid) {
        this.esrbid = esrbid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public List<Integer> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Integer> platforms) {
        this.platforms = platforms;
    }


}
