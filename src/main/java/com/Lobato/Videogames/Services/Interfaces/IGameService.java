package com.Lobato.Videogames.Services.Interfaces;

import com.Lobato.Videogames.permanece.DTOs.DTOVideogame;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IGameService {
    Integer addNewVideogame(DTOVideogame dtoVideogame, MultipartFile multipartFile) throws IOException;
    void deleteVideogame(Integer id);
    void deletePlatformToVideogame(Integer platformId, Integer videogameId);
    void deleteGenreToVideogame(Integer genreId, Integer videogameId);
}
