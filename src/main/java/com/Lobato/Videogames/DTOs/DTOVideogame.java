package com.Lobato.Videogames.DTOs;

import com.Lobato.Videogames.Entities.GenreEntity;

import java.util.List;

public class DTOVideogame {

    private String name;
    private Integer esrbid;
    private String imgUrl;
    private double price;
    private String specs;
    private List<Integer> genres;
    private List<Integer> platforms;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
