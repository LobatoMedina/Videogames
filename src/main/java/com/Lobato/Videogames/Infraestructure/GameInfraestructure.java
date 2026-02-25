package com.Lobato.Videogames.Infraestructure;

import com.Lobato.Videogames.Entities.GameEntity;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameInfraestructure extends CrudRepository<GameEntity, Integer> {
    @Procedure(name = "sp_gameAdd")
    Integer addNewGame(
            @Param("_nombre") String name,
            @Param("_srbId") Integer srbId,
            @Param("_urlImage") String url,
            @Param("_specs") String specs,
            @Param("_price") double price

    );
    @Procedure(procedureName = "sp_add_genreToMovie")
    void addNewGenre(@Param("_GameId") int gameId,@Param("_genreId") int genre);

    @Procedure(procedureName = "sp_remove_genreToVIdeogame")
    void removeGenre(@Param("_idVideogame") int gameId,@Param("_idGenre") int genre);

    @Procedure(procedureName = "sp_add_platformToGame")
    void addNewPlatform(@Param("_GameId") int gameId,@Param("_idGenre") int genre);

    @Procedure(procedureName = "sp_remove_platform")
    void removePlatform(@Param("_idVideogame") int gameId,@Param("_idPlatform") int genre);
}
