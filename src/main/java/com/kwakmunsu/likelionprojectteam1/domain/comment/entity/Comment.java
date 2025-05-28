package com.kwakmunsu.likelionprojectteam1.domain.comment.entity;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_comment_id")
    private Long parentCommentId;  // 부모 댓글 (대댓글 구현 가능)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(nullable = false)
    private String content;

    @Builder(access = AccessLevel.PRIVATE)
    private Comment(
            Member member,
            Recipe recipe,
            Long parentCommentId,
            String content
    ) {
        this.member = member;
        this.recipe = recipe;
        this.parentCommentId = parentCommentId;
        this.content = content;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    public static Comment create(
            Member member,
            Recipe recipe,
            Long parentCommentId,
            String content
    ) {
        return Comment.builder()
                .member(member)
                .recipe(recipe)
                .parentCommentId(parentCommentId)
                .content(content)
                .build();
    }

    public boolean isRoot() {
        return parentCommentId == null;
    }

}