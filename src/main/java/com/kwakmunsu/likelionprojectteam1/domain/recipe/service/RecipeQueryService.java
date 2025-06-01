package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipePaginationServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipeSearchServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipePaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeQueryService {

    private final RecipeRepository recipeRepository;

    public RecipePaginationResponse getRecipes(Long memberId, RecipePaginationServiceRequest request) {
        return recipeRepository.findAllByPagination(request.toDomainRequest(memberId));
    }

    public RecipeDetailResponse getRecipe(Long recipeId) {
        return recipeRepository.getRecipe(recipeId);
    }

    public RecipePaginationResponse search(RecipeSearchServiceRequest request) {
        return recipeRepository.search(request.query(), request.page());
    }

}