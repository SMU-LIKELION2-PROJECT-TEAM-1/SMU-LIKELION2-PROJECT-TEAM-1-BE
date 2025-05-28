package com.kwakmunsu.likelionprojectteam1.domain.comment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    public void deleteByRecipeId(Long recipeId) {
        commentJpaRepository.deleteByRecipeId(recipeId);
    }

}