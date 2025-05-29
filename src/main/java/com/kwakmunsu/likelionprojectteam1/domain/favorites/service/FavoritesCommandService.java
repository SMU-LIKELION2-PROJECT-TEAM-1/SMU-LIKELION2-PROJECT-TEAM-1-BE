package com.kwakmunsu.likelionprojectteam1.domain.favorites.service;

import com.kwakmunsu.likelionprojectteam1.domain.favorites.entity.Favorites;
import com.kwakmunsu.likelionprojectteam1.domain.favorites.repository.FavoritesRepository;
import com.kwakmunsu.likelionprojectteam1.domain.like.entity.Like;
import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FavoritesCommandService {

    private final FavoritesRepository favoritesRepository;
    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void favorites(Long recipeId, Long memberId) {
        if (recipeRepository.existsById(recipeId)) {
            if (favoritesRepository.existsByRecipeIdAndMemberId(recipeId, memberId)) {
                log.info("회원 id: {} 님이 레시피 id: {} 의 찜을 취소했습니다,", memberId, recipeId);
                favoritesRepository.delete(recipeId, memberId);
                return;
            }

            log.info("회원 id: {} 님이 레시피 id: {} 을 찜했습니다,", memberId, recipeId);
            Favorites favorites = createFavorites(recipeId, memberId);
            favoritesRepository.create(favorites);
            return;
        }
        throw new NotFoundException(ErrorMessage.NOT_FOUND_RECIPE.getMessage());
    }

    private Favorites createFavorites(Long recipeId, Long memberId) {
        Recipe recipe = recipeRepository.findById(recipeId);
        Member member = memberRepository.findById(memberId);

        return Favorites.builder()
                .recipe(recipe)
                .member(member)
                .build();
    }

}