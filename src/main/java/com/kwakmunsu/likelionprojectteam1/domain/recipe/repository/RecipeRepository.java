package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecipeRepository {

    private final RecipeJpaRepository recipeJpaRepository;

}