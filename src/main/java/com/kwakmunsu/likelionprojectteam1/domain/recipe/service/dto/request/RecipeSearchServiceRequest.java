package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request;

public record RecipeSearchServiceRequest(
        String query,
        int page
) {

}