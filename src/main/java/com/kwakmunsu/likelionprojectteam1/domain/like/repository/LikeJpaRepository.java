package com.kwakmunsu.likelionprojectteam1.domain.like.repository;

import com.kwakmunsu.likelionprojectteam1.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {

    void deleteByRecipeId(Long recipeId);

    boolean existsByRecipeIdAndMemberId(Long recipeId, Long memberId);

    void deleteByRecipeIdAndMemberId(Long recipeId, Long memberId);

}