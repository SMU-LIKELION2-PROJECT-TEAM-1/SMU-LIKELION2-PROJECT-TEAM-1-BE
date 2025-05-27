package com.kwakmunsu.likelionprojectteam1.domain.favorites.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "favorites")
@Entity
public class Favorites {

    @Id
    private Long id;

}