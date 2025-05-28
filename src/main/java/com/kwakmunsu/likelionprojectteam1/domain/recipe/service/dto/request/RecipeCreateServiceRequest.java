package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request;

import lombok.Builder;

@Builder
public record RecipeCreateServiceRequest(
        Long memberId,
        String boardType,
        String title,
        String introduction,
        String occasion,
        int cookingTime,
        String purpose,
        String foodType,
        String difficulty,
        String ingredients,
        String content
) {

}