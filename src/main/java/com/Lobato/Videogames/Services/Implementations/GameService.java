package com.Lobato.Videogames.Services.Implementations;


import com.Lobato.Videogames.permanece.DTOs.DTOVideogame;
import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import com.Lobato.Videogames.permanece.Infraestructure.EsrbRepository;
import com.Lobato.Videogames.permanece.Infraestructure.GameInfraestructure;
import com.Lobato.Videogames.Services.Interfaces.IGameService;
import com.Lobato.Videogames.permanece.Infraestructure.GenreRepository;
import com.Lobato.Videogames.permanece.Infraestructure.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService implements IGameService {

    @Autowired
    private EsrbRepository esrbRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private PlatformRepository platformRepository;
    @Autowired
    private GameInfraestructure gameInfraestructure;
    private final Path root = Paths.get("uploads");
    private static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/webp");
    @Transactional
    @Override
    public Integer addNewVideogame(DTOVideogame dtoVideogame, MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new IOException("Formato de archivo no permitido. Solo se aceptan JPG, PNG y WEBP.");
        }
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = LocalTime.now() + "."+ extension ;

        Files.copy(file.getInputStream(), this.root.resolve(fileName));
        Integer id_game= gameInfraestructure.addNewGame(
                dtoVideogame.getName(),
                dtoVideogame.getEsrbid(),
                fileName,
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

    @Override
    public List<EsrbEntity> getAllEsrb() {
        return esrbRepository.findAll();
    }

    @Override
    public List<GenreEntity> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public List<PlatformEntity> getAllPlatforms() {
        return platformRepository.findAll();
    }

}
