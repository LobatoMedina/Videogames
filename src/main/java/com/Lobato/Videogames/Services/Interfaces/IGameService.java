package com.Lobato.Videogames.Services.Interfaces;

import com.Lobato.Videogames.permanece.DTOs.*;
import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IGameService {
    Integer addNewVideogame(VideoGameInDTO dtoVideogame, MultipartFile multipartFile) throws IOException;
    String deleteVideogame(Integer id);
    String updateVideogame(VideoGameInDTO videogame, MultipartFile multipartFile);
    DTOVideogame getVideoGameById(Integer id);
    List<DTOVideogame> getAllVideogames();
    List<EsrbDTO> getAllEsrb();
    List<GenreDTO> getAllGenres();
    List<PlatformDTO> getAllPlatforms();
}
