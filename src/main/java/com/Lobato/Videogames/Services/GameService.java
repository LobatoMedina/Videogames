package com.Lobato.Videogames.Services;


import com.Lobato.Videogames.Infraestructure.GameInfraestructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameInfraestructure gameInfraestructure;

    public void addNewVideogame(){

    }

}
