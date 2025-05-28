package com.kwakmunsu.likelionprojectteam1.domain.comment.repository;

import com.kwakmunsu.likelionprojectteam1.domain.comment.entity.Comment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    void deleteByRecipeId(Long recipeId);

    Optional<Comment> findByIdAndMemberId(Long id, Long memberId);

    boolean existsByIdAndMemberId(Long id, Long memberId);

}