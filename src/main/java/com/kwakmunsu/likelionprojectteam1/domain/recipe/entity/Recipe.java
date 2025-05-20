package com.kwakmunsu.likelionprojectteam1.domain.recipe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "recipe")
@Entity
public class Recipe {

    @Id
    private Long id;

}