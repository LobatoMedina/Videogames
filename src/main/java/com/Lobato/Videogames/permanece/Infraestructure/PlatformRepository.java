package com.Lobato.Videogames.permanece.Infraestructure;

import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity,Integer> {
    @Procedure(name ="find_platforms")
    List<PlatformEntity> getAllPlatformsById(@Param("_gameId") Integer id);

}
