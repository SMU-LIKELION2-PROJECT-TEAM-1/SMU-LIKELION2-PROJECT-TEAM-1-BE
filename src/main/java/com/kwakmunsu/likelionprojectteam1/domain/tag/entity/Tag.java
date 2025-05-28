package com.kwakmunsu.likelionprojectteam1.domain.tag.entity;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.dto.TagUpdateDomainRequest;
import com.kwakmunsu.likelionprojectteam1.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Enumerated(EnumType.STRING)
    private Occasion occasion;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Builder
    private Tag(
            Recipe recipe,
            Occasion occasion,
            FoodType foodType,
            Purpose purpose
    ) {
        this.recipe = recipe;
        this.occasion = occasion;
        this.foodType = foodType;
        this.purpose = purpose;
    }

    public void updateTag(TagUpdateDomainRequest request) {
        this.occasion = Occasion.valueOf(request.occasion());
        this.foodType = FoodType.valueOf(request.foodType());
        this.purpose = Purpose.valueOf(request.purpose());
    }

}