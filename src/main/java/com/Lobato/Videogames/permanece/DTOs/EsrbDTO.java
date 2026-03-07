package com.Lobato.Videogames.permanece.DTOs;

import com.Lobato.Videogames.permanece.Entities.EsrbEntity;

public class EsrbDTO {
    private Integer id;
    private String name;
    private Integer EdadMin;

    public EsrbDTO() {
    }

    public EsrbDTO(Integer id, String name, Integer edadMin) {
        this.id = id;
        this.name = name;
        this.EdadMin = edadMin;
    }

    public EsrbDTO(EsrbEntity esrbEntity) {
        this.id = esrbEntity.getId();
        this.name = esrbEntity.getEsrb_esrb();
        this.EdadMin = esrbEntity.getEsrb_LimitAge();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEdadMin() {
        return EdadMin;
    }

    public void setEdadMin(Integer edadMin) {
        EdadMin = edadMin;
    }
}
