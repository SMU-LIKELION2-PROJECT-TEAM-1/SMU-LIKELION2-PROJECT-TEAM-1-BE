package com.kwakmunsu.likelionprojectteam1.domain.ingredient.entity;

import com.kwakmunsu.likelionprojectteam1.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "ingredient")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Ingredient extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Builder
    private Ingredient(String title, String description) {
        this.title = title;
        this.description = description;
    }

}