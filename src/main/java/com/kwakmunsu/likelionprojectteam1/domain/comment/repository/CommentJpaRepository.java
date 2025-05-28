package com.kwakmunsu.likelionprojectteam1.domain.comment.repository;

import com.kwakmunsu.likelionprojectteam1.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    void deleteByRecipeId(Long recipeId);

}