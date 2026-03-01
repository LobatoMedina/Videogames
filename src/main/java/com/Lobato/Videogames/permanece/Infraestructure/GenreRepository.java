package com.Lobato.Videogames.permanece.Infraestructure;

import com.Lobato.Videogames.permanece.Entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface  GenreRepository extends JpaRepository<GenreEntity, Integer> {

}
