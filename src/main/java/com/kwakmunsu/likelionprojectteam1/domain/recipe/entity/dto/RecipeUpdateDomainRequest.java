package com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.dto;

import lombok.Builder;

@Builder
public record RecipeUpdateDomainRequest(
        String boardType,
        String title,
        String introduction,
        int cookingTime,
        String difficulty,
        String ingredients,
        String content
) {

}