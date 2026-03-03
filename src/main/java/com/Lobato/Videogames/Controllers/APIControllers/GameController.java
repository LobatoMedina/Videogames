package com.Lobato.Videogames.Controllers.APIControllers;

import com.Lobato.Videogames.permanece.DTOs.DTOVideogame;
import com.Lobato.Videogames.Services.Interfaces.IGameService;
import com.Lobato.Videogames.permanece.DTOs.EsrbDTO;
import com.Lobato.Videogames.permanece.DTOs.GenreDTO;
import com.Lobato.Videogames.permanece.DTOs.PlatformDTO;
import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/genre")
    public List<GenreDTO> getAllGenres(){
        return gameService.getAllGenres();
    }
    @GetMapping("/platforms")
    public List<PlatformDTO> getAllPlatform(){
        return gameService.getAllPlatforms();
    }
    @GetMapping("/esrb")
    public List<EsrbDTO> getAllEsrb(){
        return gameService.getAllEsrb();
    }
    @PostMapping("/update")
    public boolean updateGame(Integer id){
        return true;
    }
}
