package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.dto.RecipePaginationDomainRequest;
import lombok.Builder;

@Builder
public record RecipePaginationServiceRequest(
        String boardType,
        String occasion,
        Integer cookingTime,
        String purpose,
        String foodType,
        String sortBy,
        String ingredient,
        int page
) {

    public RecipePaginationDomainRequest toDomainRequest() {
        return RecipePaginationDomainRequest.builder()
                .boardType(boardType)
                .occasion(occasion)
                .cookingTime(cookingTime)
                .purpose(purpose)
                .foodType(foodType)
                .sortBy(sortBy)
                .ingredient(ingredient)
                .page(page)
                .build();
    }

}