package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipePaginationServiceRequest;
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

}