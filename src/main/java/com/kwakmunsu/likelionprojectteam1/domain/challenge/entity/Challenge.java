package com.kwakmunsu.likelionprojectteam1.domain.challenge.entity;

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

@Table(name = "challenge")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Challenge extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingredient;

    private String description;

    @Builder
    private Challenge(String ingredient, String description) {
        this.ingredient = ingredient;
        this.description = description;
    }

    public void updateChallenge(String ingredient, String description) {
        this.ingredient = ingredient;
        this.description = description;
    }

}