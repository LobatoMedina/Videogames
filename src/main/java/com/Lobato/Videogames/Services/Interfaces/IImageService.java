package com.Lobato.Videogames.Services.Interfaces;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IImageService {
    byte[] returnImageByName(String name);
    boolean deleteImageByName(String name);
    List<byte[]> returnAllImage();


}
