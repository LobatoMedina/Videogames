package com.Lobato.Videogames.Services.Interfaces;

import com.Lobato.Videogames.permanece.DTOs.DTOVideogame;
import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IGameService {
    Integer addNewVideogame(DTOVideogame dtoVideogame, MultipartFile multipartFile) throws IOException;
    void deleteVideogame(Integer id);
    void deletePlatformToVideogame(Integer platformId, Integer videogameId);
    void deleteGenreToVideogame(Integer genreId, Integer videogameId);
    List<EsrbEntity> getAllEsrb();
    List<GenreEntity> getAllGenres();
    List<PlatformEntity> getAllPlatforms();
}
