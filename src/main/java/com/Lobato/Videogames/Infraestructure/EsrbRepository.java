package com.Lobato.Videogames.Infraestructure;

import com.Lobato.Videogames.Entities.EsrbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsrbRepository extends JpaRepository<EsrbEntity, Integer> {
}
