package com.Lobato.Videogames.Entities;


import jakarta.persistence.*;


@NamedStoredProcedureQuery(name = "sp_gameAdd", procedureName = "sp_add_videogame", parameters = {
        @StoredProcedureParameter(name = "_nombre", mode = ParameterMode.IN,type = String.class),
        @StoredProcedureParameter(name = "_srbId", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "_urlImage", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "_specs", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "_price", mode = ParameterMode.IN, type = Double.class),
        @StoredProcedureParameter(name = "_id", mode = ParameterMode.OUT, type = Integer.class)
})
@Entity
@Table(name = "tbl_ope_videogames")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private int esrb_id;
    private String author;
    private String specs;
    private double price;


}
