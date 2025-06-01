package com.kwakmunsu.likelionprojectteam1.domain.comment.service;

import com.kwakmunsu.likelionprojectteam1.domain.comment.entity.Comment;
import com.kwakmunsu.likelionprojectteam1.domain.comment.repository.CommentRepository;
import com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.request.CommentCreateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.request.CommentUpdateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.response.CommentCreateResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import com.kwakmunsu.likelionprojectteam1.global.exception.UnAuthenticationException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentCommandService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    public CommentCreateResponse create(CommentCreateServiceRequest request, Long recipeId) {
        // 부모 댓글 즉 첫 댓글일 경우 null 값이 저장된다. 대댓글 일 경우 부모 댓글 id 저장
        Long parentId = findParent(request);
        Member member = memberRepository.findById(request.memberId());
        Recipe recipe = recipeRepository.findById(recipeId);

        Comment comment = getComment(parentId, member, recipe, request.content());
        Comment savedComment = commentRepository.save(comment);

        return CommentCreateResponse.from(
                savedComment.getId(),
                savedComment.getParentCommentId(),
                savedComment.getContent(),
                savedComment.getCreatedAt()
        );
    }

    @Transactional
    public void update(CommentUpdateServiceRequest request, Long commentId) {
        Comment comment = commentRepository.findByIdAndMemberId(commentId, request.memberId());
        comment.updateContent(request.content());
    }

    @Transactional
    public void delete(Long commentId, Long memberId) {
        Comment comment = commentRepository.findByIdAndMemberId(commentId, memberId);
        // 부모 댓글일 경우 하위 댓글을 다 삭제 후 부모 댓글 삭제
        if (comment.isRoot()) {
            commentRepository.deleteByParentCommentId(commentId);
        }
        commentRepository.deleteById(commentId);
    }

    private Long findParent(CommentCreateServiceRequest request) {
        if (request.parentCommentId() == null) { // 첫 댓글인 경우
            return null;
        }

        return commentRepository.findById(request.parentCommentId())
                .filter(Comment::isRoot) // 상위 댓글이 루트 댓글인지 확인
                .map(Comment::getId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_COMMENT.getMessage()));
    }

    // TODO: null 값 대신 다른 방안 생각해보기
    private Comment getComment(
            Long parentId,
            Member member,
            Recipe recipe,
            String content
    ) {
        return Comment.create(
                member,
                recipe,
                parentId,
                content
        );
    }

}