package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.dto;

import lombok.Builder;

@Builder
public record RecipePaginationDomainRequest(
        Long memberId,
        String boardType,
        String occasion,
        Integer cookingTime,
        String purpose,
        String foodType,
        String sortBy,
        String ingredient,
        int page
) {

}