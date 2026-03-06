package com.Lobato.Videogames.Services.Implementations;

import com.Lobato.Videogames.Services.Interfaces.IImageService;
import com.Lobato.Videogames.permanece.Infraestructure.GameInfraestructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ImageService implements IImageService {

    private final Path root = Paths.get("uploads");
    @Override
    public Resource returnImageByName(String name) {
        try {
            Path file = root.resolve(name);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("No se pudo leer el archivo o no existe.");
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo encontrar el archivo: " + e.getMessage());
        }
    }
}
