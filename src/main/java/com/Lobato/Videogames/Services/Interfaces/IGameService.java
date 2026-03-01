package com.Lobato.Videogames.Services.Interfaces;

import com.Lobato.Videogames.permanece.DTOs.DTOVideogame;

public interface IGameService {
    Integer addNewVideogame(DTOVideogame dtoVideogame);
    void deleteVideogame(Integer id);
    void deletePlatformToVideogame(Integer platformId, Integer videogameId);
    void deleteGenreToVideogame(Integer genreId, Integer videogameId);
}
