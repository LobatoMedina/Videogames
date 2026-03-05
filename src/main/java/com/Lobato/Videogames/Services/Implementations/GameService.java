package com.Lobato.Videogames.Services.Implementations;


import com.Lobato.Videogames.permanece.DTOs.*;
import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import com.Lobato.Videogames.permanece.Entities.GameEntity;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public Integer addNewVideogame(VideoGameInDTO dtoVideogame, MultipartFile file) throws IOException {
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
    public String deleteVideogame(Integer id) {
        try{
            gameInfraestructure.deleteVideogame(id);
            return  "agregado correctamente";
        }catch (Exception e) {
            return "error al eliminar"+ e.getMessage();
        }

    }

    @Override
    public String updateVideogame(DTOVideogame videogame, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public DTOVideogame getVideoGameById(Integer id) {
        GameEntity esrb=gameInfraestructure.findById(id).get();
        return new DTOVideogame(esrb.getId(),
                esrb.getName(),
                esrb.getEsrb_id(),
                "url",
                esrb.getAuthor(),
                esrb.getSpecs(),
                esrb.getPrice(),
                null, null);
    }

    @Override
    public List<DTOVideogame> getAllVideogames() {
        return List.of();
    }

    @Override
    public List<Byte[]> getAllImage() {
        return List.of();
    }

    @Override
    public Byte[] getImageById(Integer id) {
        return new Byte[0];
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
    public List<EsrbDTO> getAllEsrb() {
        List<EsrbDTO> lista = new ArrayList<>();
        try{
            Iterable<EsrbEntity> data = esrbRepository.findAll();
            for (var element : data){
                lista.add(new EsrbDTO(
                        element.getId(), element.getEsrb_esrb(), element.getEsrb_LimitAge()
                ));
            }
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
            Iterable<GenreEntity> data = genreRepository.findAll();
            for (var element : data){
                lista.add(new GenreDTO(
                    element.getId(), element.getGenre()
                ));
            }
        }catch (Exception e){
            throw new RuntimeException("Mi bombo"+ e.getMessage());
        }

        return  lista;
    }

    @Override
    public List<PlatformDTO> getAllPlatforms() {
        List<PlatformDTO> lista = new ArrayList<>();
        try{
            Iterable<PlatformEntity> data = platformRepository.findAll();
            for (var element : data){
                lista.add(new PlatformDTO(
                        element.getPlatformId(), element.getPlatform_platform()
                ));
            }       }catch (Exception e){
            throw new RuntimeException("Mi bombo"+ e.getMessage());
        }

        return lista;
    }

}
