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
    public void addNewVideogame(DTOVideogame dtoVideogame){
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
    }

}
