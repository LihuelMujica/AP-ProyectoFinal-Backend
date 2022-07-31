package com.argentinaprograma.portfolio.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "proyecto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer id;
    @Column(name = "titulo")
    private String title;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "imagen")
    private String image;
}
