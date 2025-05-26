package com.kwakmunsu.likelionprojectteam1.domain.recipe.entity;

import com.kwakmunsu.likelionprojectteam1.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "recipe")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recipe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Builder
    private Recipe(String title) {
        this.title = title;
    }

}