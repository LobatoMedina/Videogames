package com.Lobato.Videogames.permanece.DTOs;

import com.Lobato.Videogames.permanece.Entities.PlatformEntity;

public class PlatformDTO {
    private Integer id;
    private String name;

    public PlatformDTO(PlatformEntity pe) {
        this.id = pe.getPlatformId();
        this.name = pe.getPlatform_platform();
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
}
