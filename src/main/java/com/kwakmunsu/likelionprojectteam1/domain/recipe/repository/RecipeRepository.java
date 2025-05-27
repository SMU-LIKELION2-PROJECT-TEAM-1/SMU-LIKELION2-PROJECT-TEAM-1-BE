package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecipeRepository {

    private final RecipeJpaRepository recipeJpaRepository;

    public Recipe save(Recipe recipe) {
       return recipeJpaRepository.save(recipe);
    }

}