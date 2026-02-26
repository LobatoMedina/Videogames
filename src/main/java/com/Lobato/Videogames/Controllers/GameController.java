package com.Lobato.Videogames.Controllers;

import com.Lobato.Videogames.DTOs.DTOVideogame;
import com.Lobato.Videogames.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private GameService gameService;
    @PostMapping("/add")
    public Boolean insertGame(
            @RequestBody DTOVideogame videogame
            ){
        try{
            gameService.addNewVideogame(videogame);
            return true; //esto es mierda pero igual lo conservo por las weas
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


}
