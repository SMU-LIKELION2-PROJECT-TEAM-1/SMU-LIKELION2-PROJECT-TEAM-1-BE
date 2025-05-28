package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import com.kwakmunsu.likelionprojectteam1.global.exception.UnAuthenticationException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecipeRepository {

    private final RecipeJpaRepository recipeJpaRepository;

    public Recipe save(Recipe recipe) {
        return recipeJpaRepository.save(recipe);
    }

    public Recipe findByIdAndMemberId(Long id, Long memberId) {
        return recipeJpaRepository.findByIdAndMemberId(id, memberId)
                .orElseThrow(() -> new UnAuthenticationException(ErrorMessage.MODIFY_UNAUTHORIZED_RECIPE.getMessage()));
    }

}