package com.Lobato.Videogames.permanece.Infraestructure;

import com.Lobato.Videogames.permanece.Entities.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<PlatformEntity,Integer> {
}
