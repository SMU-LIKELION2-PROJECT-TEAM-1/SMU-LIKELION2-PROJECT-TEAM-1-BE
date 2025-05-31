package com.kwakmunsu.likelionprojectteam1.domain.comment.repository;

import com.kwakmunsu.likelionprojectteam1.domain.comment.entity.Comment;
import com.kwakmunsu.likelionprojectteam1.global.exception.UnAuthenticationException;
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

    public void deleteByParentCommentId(Long parentCommentId) {
        commentJpaRepository.deleteByParentCommentId(parentCommentId);
    }

    public void deleteById(Long id) {
        commentJpaRepository.deleteById(id);
    }

    public Comment findByIdAndMemberId(Long id, Long memberId) {
        return commentJpaRepository.findByIdAndMemberId(id, memberId)
                .orElseThrow(() -> new UnAuthenticationException(ErrorMessage.MODIFY_UNAUTHORIZED.getMessage()));
    }

}