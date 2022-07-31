package com.argentinaprograma.portfolio.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "red_social")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_red_social")
    private Integer id;
    @Column(name = "imagen")
    private String image;
    @Column(name = "url")
    private String url;
}
