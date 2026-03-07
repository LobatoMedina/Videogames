package com.Lobato.Videogames.Services.Implementations;


import com.Lobato.Videogames.permanece.DTOs.*;
import com.Lobato.Videogames.permanece.Entities.*;
import com.Lobato.Videogames.permanece.Infraestructure.*;
import com.Lobato.Videogames.Services.Interfaces.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private GameViewRepo gameViewRepo;

    private final Path root = Paths.get("uploads");
    private static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/webp");

    @Transactional
    @Override
    public Integer addNewVideogame(VideoGameInDTO dtoVideogame, MultipartFile file) throws IOException {
        String fileName = insertImage(file);
        Integer id_game= gameInfraestructure.addNewGame(
                dtoVideogame.getName(),
                dtoVideogame.getEsrbid(),
                fileName,
                dtoVideogame.getAuthor(),
                dtoVideogame.getSpecs(),
                dtoVideogame.getPrice(),
                dtoVideogame.getStock(),
                dtoVideogame.getDemo()
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
    public String deleteVideogame(Integer id) {
        try{
            gameInfraestructure.deleteVideogame(id);
            return  "eliminado correctamente";
        }catch (Exception e) {
            return "error al eliminar"+ e.getMessage();
        }

    }

    @Override
    public String updateVideogame(DTOVideogame videogame, MultipartFile multipartFile) throws IOException {
        //Hola yo del futuro
        // Elimina la imagen buscandolo con el id
        try {
            Path file = root.resolve(gameInfraestructure.getUrlImage(videogame.getId()));
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo eliminar el archivo: " + e.getMessage());
        }
        //ahora si, añade la nueva imagen aplicando el mismo formato
        String fileName = insertImage(multipartFile);
        gameInfraestructure.updateGame(videogame.getId(),
                videogame.getName(),
                videogame.getEsrbDTO().getId(),
                fileName,
                videogame.getAuthor(),
                videogame.getSpecs(),
                videogame.getPrice(),
                videogame.getStock(),
                videogame.getDemo());
        gameInfraestructure.removeGenre(videogame.getId());
        gameInfraestructure.removePlatform(videogame.getId());
        if(videogame.getGenres() != null){
            for(GenreDTO id_genre : videogame.getGenres()){
                gameInfraestructure.addNewGenre(videogame.getId(), id_genre.getId());
            }
        }
        if(videogame.getPlatforms() != null){
            for(PlatformDTO id_platform : videogame.getPlatforms()){
                gameInfraestructure.addNewPlatform(videogame.getId(), id_platform.getId());
            }
        }
        return "Juego actualizado";

    }
    public String insertImage(MultipartFile multipartFile) throws IOException {
        String contentType = multipartFile.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new IOException("Formato de archivo no permitido. Solo se aceptan JPG, PNG y WEBP.");
        }
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
        String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String fileName = LocalTime.now() + "."+ extension ;
        Files.copy(multipartFile.getInputStream(), this.root.resolve(fileName));
        return  fileName;
    }
    @Override
    public DTOVideogame getVideoGameById(Integer id) {
        GameViewEntity gve = gameViewRepo.getReferenceById(id);
        return new DTOVideogame(gve.getId(),
                gve.getTitle(),
                (EsrbDTO) esrbRepository.findById(id).stream().map(EsrbDTO::new),
               "https://localhost:8080/images/"+ gve.getImage_url(),
                gve.getAuthor(),
                gve.getSpecs(),
                gve.getPrice(),
                genreRepository.getAllGenresFromGame(id).stream().map(GenreDTO::new).collect(Collectors.toList()),
                platformRepository.getAllPlatformsById(id).stream().map(PlatformDTO::new).collect(Collectors.toList()),
                gve.getVideogames_stock(),
                gve.getVideogames_Demo()
                );
    }

    @Override
    public List<DTOVideogame> getAllVideogames() {
        List<DTOVideogame> videogames = new ArrayList<>();
        for(var game : gameViewRepo.findAll()){
            int tpmId= game.getId();
            videogames.add(new DTOVideogame(tpmId,
                    game.getTitle(),
                    (EsrbDTO) esrbRepository.findById(tpmId).stream().map(EsrbDTO::new),
                    "https://localhost:8080/images/"+game.getImage_url(),
                    game.getAuthor(),
                    game.getSpecs(),
                    game.getPrice(),
                    genreRepository.getAllGenresFromGame(tpmId).stream().map(GenreDTO::new).collect(Collectors.toList()),
                    platformRepository.getAllPlatformsById(tpmId).stream().map(PlatformDTO::new).collect(Collectors.toList()),
                    game.getVideogames_stock(),
                    game.getVideogames_Demo()
            ));
        }

        return videogames;
    }


    @Override
    @Transactional
    public List<EsrbDTO> getAllEsrb() {
        List<EsrbDTO> lista;
        try{
            lista = esrbRepository.findAll().stream().map(EsrbDTO::new).collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException("Mi bombo"+ e.getMessage());
        }

        return lista;
    }

    @Override
    @Transactional
    public List<GenreDTO> getAllGenres() {
        List<GenreDTO> lista = new ArrayList<>();

        try{
            lista = genreRepository.findAll().stream().map(GenreDTO::new).collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException("Mi bombo"+ e.getMessage());
        }

        return  lista;
    }

    @Override
    public List<PlatformDTO> getAllPlatforms() {
        List<PlatformDTO> lista = new ArrayList<>();
        try{
            lista = platformRepository.findAll().stream().map(PlatformDTO::new).collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException("Mi bombo"+ e.getMessage());
        }

        return lista;
    }

}