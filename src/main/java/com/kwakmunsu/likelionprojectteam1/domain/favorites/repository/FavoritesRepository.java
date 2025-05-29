package com.kwakmunsu.likelionprojectteam1.domain.favorites.repository;

import com.kwakmunsu.likelionprojectteam1.domain.favorites.entity.Favorites;
import com.kwakmunsu.likelionprojectteam1.domain.like.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FavoritesRepository {

    private final FavoritesJpaRepository favoritesJpaRepository;

    public void deleteByRecipeId(Long recipeId) {
        favoritesJpaRepository.deleteByRecipeId(recipeId);
    }

    public boolean existsByRecipeIdAndMemberId(Long recipeId, Long memberId ) {
        return favoritesJpaRepository.existsByRecipeIdAndMemberId(recipeId, memberId);
    }

    public void create(Favorites favorites) {
        favoritesJpaRepository.save(favorites);
    }

    public void delete(Long recipeId, Long memberId) {
        favoritesJpaRepository.deleteByRecipeIdAndMemberId(recipeId, memberId);
    }

}