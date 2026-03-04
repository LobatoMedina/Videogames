package com.Lobato.Videogames.permanece.DTOs;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class VideoGameInDTO {
    private String name;
    private Integer esrbid;
    public String author;
    private String specs;
    private double price;
    private List<Integer> genres;
    private List<Integer> platforms;

    public VideoGameInDTO() {
    }

    public VideoGameInDTO(String name, Integer esrbid, String author, String specs, double price, List<Integer> genres, List<Integer> platforms) {
        this.name = name;
        this.esrbid = esrbid;
        this.author = author;
        this.specs = specs;
        this.price = price;
        this.genres = genres;
        this.platforms = platforms;
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
