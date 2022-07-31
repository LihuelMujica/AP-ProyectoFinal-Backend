package com.argentinaprograma.portfolio.persistence.entity;

import javax.persistence.*;

@Entity(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill")
    private Integer id;
    @Column(name = "titulo")
    private String title;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "imagen")
    private String image;
}
