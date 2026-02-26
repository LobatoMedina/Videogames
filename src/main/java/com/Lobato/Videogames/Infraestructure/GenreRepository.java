package com.Lobato.Videogames.Infraestructure;

import com.Lobato.Videogames.Entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface  GenreRepository extends JpaRepository<GenreEntity, Integer> {

}
