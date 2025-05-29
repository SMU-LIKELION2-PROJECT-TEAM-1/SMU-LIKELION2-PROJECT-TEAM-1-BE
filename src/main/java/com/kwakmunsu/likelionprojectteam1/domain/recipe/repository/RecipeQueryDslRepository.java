package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository;

import static com.kwakmunsu.likelionprojectteam1.domain.comment.entity.QComment.comment;
import static com.kwakmunsu.likelionprojectteam1.domain.favorites.entity.QFavorites.favorites;
import static com.kwakmunsu.likelionprojectteam1.domain.image.QImage.image;
import static com.kwakmunsu.likelionprojectteam1.domain.like.entity.QLike.like;
import static com.kwakmunsu.likelionprojectteam1.domain.member.entity.QMember.member;
import static com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.QRecipe.recipe;
import static com.kwakmunsu.likelionprojectteam1.domain.view.entity.QView.view;
import static com.querydsl.core.types.Projections.constructor;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.MyPageOption;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipeInfinityPreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeAuthorResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeCountResponse;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecipeQueryDslRepository {

    private final JPAQueryFactory query;
    private static final int PAGE_SIZE = 10;

    public RecipeInfinityPreviewResponse findByMemberId(
            Long memberId,
            Long lastRecipeId,
            MyPageOption option
    ) {
        List<RecipePreviewResponse> responses = query.select(constructor(
                        RecipePreviewResponse.class,
                        recipe.id.as("recipeId"),
                        image.name.min(),
                        recipe.title,
                        recipe.introduction,
                        recipe.difficulty.stringValue(),
                        constructor(RecipeAuthorResponse.class,
                                member.id.as("memberId"),
                                member.nickname,
                                member.grade.stringValue()
                        ),
                        constructor(RecipeCountResponse.class,
                                like.countDistinct(),
                                view.countDistinct(),
                                comment.countDistinct(),
                                favorites.countDistinct()
                        ))
                )
                .from(recipe)
                .leftJoin(recipe.member, member)
                .leftJoin(image).on(
                        recipe.id.eq(image.recipe.id)
                                .and(image.id.eq(
                                        selectMinImageIdByRecipe()
                                ))
                )
                .leftJoin(like).on(recipe.id.eq(like.recipe.id))
                .leftJoin(view).on(recipe.id.eq(view.recipe.id))
                .leftJoin(favorites).on(recipe.id.eq(favorites.recipe.id))
                .leftJoin(comment).on(recipe.id.eq(comment.recipe.id))
                .where(
                        getOptionExpression(option, memberId),
                        cursorIdCondition(lastRecipeId)
                )
                .orderBy(recipe.id.desc())
                .groupBy(recipe.id)
                .limit(PAGE_SIZE + 1)
                .fetch();

        boolean hasNext = responses.size() > PAGE_SIZE;
        List<RecipePreviewResponse> limitedPage = getLimitedPage(responses, hasNext);
        Long nextCursorOrNull = getNextCursorOrNull(responses, hasNext);

        return RecipeInfinityPreviewResponse.builder()
                .responses(limitedPage)
                .nextCursorId(nextCursorOrNull)
                .hasNext(hasNext)
                .build();
    }

    private Expression<Long> selectMinImageIdByRecipe() {
        return JPAExpressions
                .select(image.id.min())
                .from(image)
                .where(image.recipe.id.eq(recipe.id));
    }

    private BooleanExpression cursorIdCondition(Long cursorId) {
        if (cursorId == null) {
            return null;
        }

        return recipe.id.lt(cursorId); // ID가 커서보다 작은 항목 조회 (내림차순 정렬 시)
    }

    private BooleanExpression getOptionExpression(MyPageOption option, Long memberId) {
        return switch (option) {
            case LIKE -> like.member.id.eq(memberId);
            case FAVORITES -> favorites.member.id.eq(memberId);
            case COMMENT -> comment.member.id.eq(memberId);
        };
    }

    private List<RecipePreviewResponse> getLimitedPage(List<RecipePreviewResponse> responses, boolean hasNext) {
        if (hasNext) {
            return responses.subList(0, PAGE_SIZE); // 실제로는 limit 만큼만 반환
        }

        return responses;
    }

    private Long getNextCursorOrNull(List<RecipePreviewResponse> responses, boolean hasNext) {
        if (hasNext) {
            return responses.get(responses.size() - 1).id();
        }

        return null;
    }

}