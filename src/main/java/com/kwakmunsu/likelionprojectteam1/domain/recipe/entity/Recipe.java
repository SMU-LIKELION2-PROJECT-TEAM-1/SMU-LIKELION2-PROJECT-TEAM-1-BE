package com.kwakmunsu.likelionprojectteam1.domain.recipe.entity;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.dto.RecipeUpdateDomainRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.dto.TagUpdateDomainRequest;
import com.kwakmunsu.likelionprojectteam1.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Table(name = "recipe")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recipe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String introduction;

    @Column(nullable = false)
    private int cookingTime;

    @Column(nullable = false)
    private String ingredients;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;

    @Embedded
    private Tag tag;

    @Builder
    private Recipe(Member member, String title, String introduction, int cookingTime, String ingredients, String content,
            Difficulty difficulty, BoardType boardType, Tag tag) {
        this.member = member;
        this.title = title;
        this.introduction = introduction;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.content = content;
        this.difficulty = difficulty;
        this.boardType = boardType;
        this.tag = tag;
    }

    // 앞단에서 유효성 검증을 마쳤기에 따로 검증을 진행하지 않음.
    public void updateRecipe(RecipeUpdateDomainRequest request) {
        this.title = request.title();
        this.introduction = request.introduction();
        this.cookingTime = request.cookingTime();
        this.difficulty = Difficulty.valueOf(request.difficulty());
        this.boardType = BoardType.valueOf(request.boardType());
        this.ingredients = request.ingredients();
        this.content = request.content();
    }

    public void updateTag(TagUpdateDomainRequest request) {
        Occasion occasion = Occasion.valueOf(request.occasion());
        FoodType foodType = FoodType.valueOf(request.foodType());
        Purpose purpose = Purpose.valueOf(request.purpose());

        tag.updateTag(occasion, foodType, purpose);
    }

}