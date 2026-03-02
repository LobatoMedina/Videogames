package com.Lobato.Videogames.Controllers.APIControllers;

import com.Lobato.Videogames.permanece.DTOs.DTOVideogame;
import com.Lobato.Videogames.Services.Interfaces.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private IGameService gameService;
    @PostMapping("/add")
    public Boolean insertGame(
            @RequestBody DTOVideogame videogame,
            @RequestParam("file")MultipartFile file
            ){
        try{
            gameService.addNewVideogame(videogame, file);
            return true; //esto es mierda pero igual lo conservo por las weas
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


}
