package com.argentinaprograma.portfolio.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "educacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_educacion")
    private Integer id;
    @Column(name = "titulo")
    private String title;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "fecha_inicio")
    private Date startDate;
    @Column(name = "fecha_fin")
    private Date endDate;
    @Column(name = "imagen")
    private String image;
}
