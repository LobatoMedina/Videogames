package com.Lobato.Videogames.Services.Implementations;


import com.Lobato.Videogames.DTOs.DTOVideogame;
import com.Lobato.Videogames.Infraestructure.GameInfraestructure;
import com.Lobato.Videogames.Services.Interfaces.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService implements IGameService {

    @Autowired
    private GameInfraestructure gameInfraestructure;
    @Transactional
    @Override
    public Integer addNewVideogame(DTOVideogame dtoVideogame){
        Integer id_game= gameInfraestructure.addNewGame(
                dtoVideogame.getName(),
                dtoVideogame.getEsrbid(),
                dtoVideogame.getImgUrl(),
                dtoVideogame.getAuthor(),
                dtoVideogame.getSpecs(),
                dtoVideogame.getPrice()
                );
        if(dtoVideogame.getGenres() != null){
            for(Integer id_genre : dtoVideogame.getGenres()){
                gameInfraestructure.addNewGenre(id_game, id_genre);
            }
        }
        if(dtoVideogame.getPlatforms() != null){
            for(Integer id_platform : dtoVideogame.getPlatforms()){
                gameInfraestructure.addNewPlatform(id_game, id_platform);
            }
        }
        return id_game;
    }

    @Override
    public void deleteVideogame(Integer id) {
        try{
            gameInfraestructure.deleteVideogame(id);
        }catch (Exception e) {
            throw new RuntimeException("error al eliminar un videojuego con la excepcion: " + e.getMessage());
        }
    }

    @Override
    public void deletePlatformToVideogame(Integer platformId, Integer videogameId) {
        try{
            gameInfraestructure.removePlatform(videogameId,platformId);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al eliminar una plataforma "+e.getMessage());
        }
    }

    @Override
    public void deleteGenreToVideogame(Integer genreId, Integer videogameId) {
        try{
            gameInfraestructure.removeGenre(videogameId,genreId);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al eliminar un genero de un videojuego "+e.getMessage());
        }
    }

}
