package com.Lobato.Videogames.Controllers.APIControllers;

import com.Lobato.Videogames.permanece.DTOs.*;
import com.Lobato.Videogames.Services.Interfaces.IGameService;
import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    private IGameService gameService;
    @PostMapping(value = "/add",consumes = {"multipart/form-data"})
    public ResponseEntity<String> insertGame(
            @RequestPart VideoGameInDTO videoGameInDTO,
            @RequestPart MultipartFile file
            ){
        try{
            gameService.addNewVideogame(videoGameInDTO, file);
            return new ResponseEntity<String>("Agregado correctamente", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
