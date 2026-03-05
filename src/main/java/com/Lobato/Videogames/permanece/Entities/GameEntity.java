package com.Lobato.Videogames.permanece.Entities;


import jakarta.persistence.*;


@NamedStoredProcedureQuery(name = "sp_gameAdd", procedureName = "sp_add_videogame", parameters = {
        @StoredProcedureParameter(name = "_nombre", mode = ParameterMode.IN,type = String.class),
        @StoredProcedureParameter(name = "_srbId", mode = ParameterMode.IN, type = Integer.class),
        @StoredProcedureParameter(name = "_urlImage", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "_author", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "_specs", mode = ParameterMode.IN, type = String.class),
        @StoredProcedureParameter(name = "_price", mode = ParameterMode.IN, type = Double.class),
        @StoredProcedureParameter(name = "_id", mode = ParameterMode.OUT, type = Integer.class)
})
@Entity
@Table(name = "tbl_ope_videogames")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VideogameId")
    private Integer id;
    @Column(name ="Videogame_videogame")
    private String name;
    @Column(name =  "esrb_id")
    private int esrb_id;

    @Column(name ="author")
    private String author;
    @Column(name = "Videogame_specs")
    private String specs;
    @Column(name = "Videogame_price")
    private double price;

    public GameEntity() {
    }

    public GameEntity(Integer id, String name, int esrb_id, String author, String specs, double price) {
        this.id = id;
        this.name = name;
        this.esrb_id = esrb_id;
        this.author = author;
        this.specs = specs;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEsrb_id() {
        return esrb_id;
    }

    public void setEsrb_id(int esrb_id) {
        this.esrb_id = esrb_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
