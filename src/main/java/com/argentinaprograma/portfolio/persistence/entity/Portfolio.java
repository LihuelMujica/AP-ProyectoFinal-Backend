package com.argentinaprograma.portfolio.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "portfolio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_portfolio")
    private Integer id;

    @Column(name = "nombre_y_apellido")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "acerca_de")
    private String about;
    @Column(name = "foto_perfil")
    private String profilePicture;
    @Column(name = "foto_portada")
    private String coverPicture;
}
