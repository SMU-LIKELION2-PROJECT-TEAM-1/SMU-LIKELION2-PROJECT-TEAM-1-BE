package com.kwakmunsu.likelionprojectteam1.domain.like.repository;

import com.kwakmunsu.likelionprojectteam1.domain.like.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LikeRepository {

    private final LikeJpaRepository likeJpaRepository;

    public void deleteByRecipeId(Long recipeId) {
        likeJpaRepository.deleteByRecipeId(recipeId);
    }
    public boolean existsByRecipeIdAndMemberId(Long recipeId, Long memberId ) {
        return likeJpaRepository.existsByRecipeIdAndMemberId(recipeId, memberId);
    }

    public void create(Like like) {
        likeJpaRepository.save(like);
    }

    public void delete(Long recipeId, Long memberId) {
        likeJpaRepository.deleteByRecipeIdAndMemberId(recipeId, memberId);
    }

}