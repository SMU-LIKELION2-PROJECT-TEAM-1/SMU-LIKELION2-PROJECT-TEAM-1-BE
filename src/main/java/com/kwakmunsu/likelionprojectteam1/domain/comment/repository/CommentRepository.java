package com.kwakmunsu.likelionprojectteam1.domain.comment.repository;

import com.kwakmunsu.likelionprojectteam1.domain.comment.entity.Comment;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    public Comment save(Comment comment) {
        return commentJpaRepository.save(comment);
    }

    public Optional<Comment> findById(Long id) {
        return commentJpaRepository.findById(id);

    }

    public void deleteByRecipeId(Long recipeId) {
        commentJpaRepository.deleteByRecipeId(recipeId);
    }

}