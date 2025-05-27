package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeQueryService {

    private final RecipeRepository recipeRepository;

}