package com.Lobato.Videogames.permanece.Infraestructure;

import com.Lobato.Videogames.permanece.Entities.GameEntity;
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
            @Param("_price") double price,
            @Param("_stock") Integer stock,
            @Param("_demo") String demo

    );
    @Procedure (name = "get_url_image", outputParameterName = "_urlImage")
    String getUrlImage(@Param("_gameId") Integer id);
    @Procedure(procedureName = "sp_add_genreToMovie")
    void addNewGenre(@Param("_GameId") Integer gameId,
                     @Param("_genreId") Integer genre);
    @Procedure(name = "update_game")
    void updateGame(@Param("_videogameId") Integer id,
                    @Param("_name") String name,
                    @Param("_esrbId") Integer esrb_id,
                    @Param("_urlImage") String image,
                    @Param("_author") String author,
                    @Param("_specs")  String specs,
                    @Param("_price")  Double price,
                    @Param("_stock") Integer stock,
                    @Param("_demo") String demo
    );
    @Procedure(procedureName = "sp_remove_genreToVideogame")
    void removeGenre(@Param("_idVideogame") Integer gameId);

    @Procedure(procedureName = "sp_add_platformToGame")
    void addNewPlatform(@Param("_GameId") Integer gameId,
                        @Param("_platform") Integer platform);

    @Procedure(procedureName = "sp_remove_platform")
    void removePlatform(@Param("_idVideogame") Integer gameId);

    @Procedure(procedureName = "sp_delete_videogame")
    void deleteVideogame(@Param("_idVideogame") Integer id);
}
