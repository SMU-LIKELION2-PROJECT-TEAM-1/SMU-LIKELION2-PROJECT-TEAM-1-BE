package com.kwakmunsu.likelionprojectteam1.domain.like.service;

import static com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.PointOption.LIKE;
import static com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.PointOption.UN_LIKE;

import com.kwakmunsu.likelionprojectteam1.domain.like.entity.Like;
import com.kwakmunsu.likelionprojectteam1.domain.like.repository.LikeRepository;
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
public class LikeCommandService {

    private final LikeRepository likeRepository;
    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void like(Long recipeId, Long memberId) {
        if (recipeRepository.existsById(recipeId)) {
            Member member = memberRepository.findById(memberId);

            if (likeRepository.existsByRecipeIdAndMemberId(recipeId, memberId)) {
                likeRepository.delete(recipeId, memberId);
                member.updatePoint(UN_LIKE.getValue());
                return;
            }

            Like like = createLike(recipeId, memberId);
            likeRepository.create(like);
            member.updatePoint(LIKE.getValue());
            return;
        }
        throw new NotFoundException(ErrorMessage.NOT_FOUND_RECIPE.getMessage());
    }

    private Like createLike(Long recipeId, Long memberId) {
        Recipe recipe = recipeRepository.findById(recipeId);
        Member member = memberRepository.findById(memberId);

        return Like.builder()
                .recipe(recipe)
                .member(member)
                .build();
    }

    private void update() {

    }

}