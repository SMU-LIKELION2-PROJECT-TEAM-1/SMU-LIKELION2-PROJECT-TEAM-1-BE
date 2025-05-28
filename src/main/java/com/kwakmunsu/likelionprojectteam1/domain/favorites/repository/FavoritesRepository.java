package com.kwakmunsu.likelionprojectteam1.domain.favorites.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FavoritesRepository {

    private final FavoritesJpaRepository favoritesJpaRepository;

    public void deleteByRecipeId(Long recipeId) {
        favoritesJpaRepository.deleteByRecipeId(recipeId);
    }

}