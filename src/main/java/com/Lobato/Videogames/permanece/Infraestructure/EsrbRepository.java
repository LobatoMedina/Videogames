package com.Lobato.Videogames.permanece.Infraestructure;

import com.Lobato.Videogames.permanece.Entities.EsrbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsrbRepository extends JpaRepository<EsrbEntity, Integer> {
}
