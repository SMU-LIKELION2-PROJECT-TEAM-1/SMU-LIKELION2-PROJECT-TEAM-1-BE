package com.kwakmunsu.likelionprojectteam1.domain.like.entity;

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

@Table(name = "likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    private Like(Member member, Recipe recipe) {
        this.member = member;
        this.recipe = recipe;
    }

}