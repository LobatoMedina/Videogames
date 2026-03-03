package com.Lobato.Videogames.permanece.Entities;


import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cat_platforms")
public class PlatformEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "platformId")
    private Integer platformId;
    @Column(name = "platform_platform")
    private String platform_platform;

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getPlatform_platform() {
        return platform_platform;
    }

    public void setPlatform_platform(String platform_platform) {
        this.platform_platform = platform_platform;
    }
}
