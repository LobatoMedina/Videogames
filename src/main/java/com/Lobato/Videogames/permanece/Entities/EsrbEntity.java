package com.Lobato.Videogames.permanece.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cat_esrb")
public class EsrbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "esrbId")
    private Integer id;
    @Column(name ="esrb_esrb")
    private String esrb_esrb;
    @Column(name = "esrb_LimitAge")
    private int esrb_LimitAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEsrb_esrb() {
        return esrb_esrb;
    }

    public void setEsrb_esrb(String esrb_esrb) {
        this.esrb_esrb = esrb_esrb;
    }

    public int getEsrb_LimitAge() {
        return esrb_LimitAge;
    }

    public void setEsrb_LimitAge(int esrb_LimitAge) {
        this.esrb_LimitAge = esrb_LimitAge;
    }
}
