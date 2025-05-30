package com.kwakmunsu.likelionprojectteam1.domain.recipe.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class Tag {

    @Enumerated(EnumType.STRING)
    private Occasion occasion;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Builder
    private Tag(
            Occasion occasion,
            FoodType foodType,
            Purpose purpose
    ) {
        this.occasion = occasion;
        this.foodType = foodType;
        this.purpose = purpose;
    }

    public void updateTag(Occasion occasion, FoodType foodType, Purpose purpose) {
        this.occasion = occasion;
        this.foodType = foodType;
        this.purpose = purpose;
    }

}