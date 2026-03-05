package com.Lobato.Videogames.Services.Implementations;

import com.Lobato.Videogames.Services.Interfaces.IImageService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ImageService implements IImageService {

    private final Path root = Paths.get("uploads");
    @Override
    public byte[] returnImageByName(String name) {

        return new byte[0];
    }

    @Override
    public boolean deleteImageByName(String name) {
        return false;
    }

    @Override
    public List<byte[]> returnAllImage() {
        return List.of();
    }
}
