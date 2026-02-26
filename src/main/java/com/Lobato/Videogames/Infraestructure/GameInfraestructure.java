package com.Lobato.Videogames.Infraestructure;

import com.Lobato.Videogames.Entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface GameInfraestructure extends JpaRepository<GameEntity, Integer> {
    @Procedure(name = "sp_gameAdd", outputParameterName = "_id")
     Integer addNewGame(
            @Param("_nombre") String name,
            @Param("_srbId") Integer srbId,
            @Param("_urlImage") String url,
            @Param("_author") String author,
            @Param("_specs") String specs,
            @Param("_price") double price

    );
    @Procedure(procedureName = "sp_add_genreToMovie")
    void addNewGenre(@Param("_GameId") Integer gameId,
                     @Param("_genreId") Integer genre);

    @Procedure(procedureName = "sp_remove_genreToVideogame")
    void removeGenre(@Param("_idVideogame") Integer gameId,
                     @Param("_idGenre") Integer genre);

    @Procedure(procedureName = "sp_add_platformToGame")
    void addNewPlatform(@Param("_GameId") Integer gameId,
                        @Param("_platform") Integer platform);

    @Procedure(procedureName = "sp_remove_platform")
    void removePlatform(@Param("_idVideogame") Integer gameId,
                        @Param("_idPlatform") Integer platform);

    @Procedure(procedureName = "sp_delete_videogame")
    void delteVideogame(@Param("_idVideogame") Integer id);
}
