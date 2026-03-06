package com.Lobato.Videogames.Controllers.APIControllers;

import com.Lobato.Videogames.permanece.DTOs.*;
import com.Lobato.Videogames.Services.Interfaces.IGameService;
import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
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
            ) {
        try {
            gameService.addNewVideogame(videoGameInDTO, file);
            return new ResponseEntity<String>("Agregado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateGame(
            @RequestPart DTOVideogame videogame,
            @RequestPart MultipartFile file){
        try{
            gameService.updateVideogame(
                    videogame, file
            );
            return ResponseEntity.ok().body("Agregado Exitosamente");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al actualizar" + e.getMessage());
        }

    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteGameById(
            @RequestParam Integer id){
        try{
            gameService.deleteVideogame(id);
            return ResponseEntity.ok().body("Se ha eliminado exitosamente");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("error al eliminar ese usuario" + e.getMessage());
        }
    }
    @GetMapping("/getGames")
    public List<DTOVideogame> getAllVideogames(){
        return gameService.getAllVideogames();
    }
    @GetMapping("/getGame/{GameId}")
    public DTOVideogame getUserById(@PathVariable("GameId") Integer GameId){
        return gameService.getVideoGameById(GameId);
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



}
