package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository;

import static com.kwakmunsu.likelionprojectteam1.domain.comment.entity.QComment.comment;
import static com.kwakmunsu.likelionprojectteam1.domain.favorites.entity.QFavorites.favorites;
import static com.kwakmunsu.likelionprojectteam1.domain.image.entity.QImage.image;
import static com.kwakmunsu.likelionprojectteam1.domain.like.entity.QLike.like;
import static com.kwakmunsu.likelionprojectteam1.domain.member.entity.QMember.member;
import static com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.QRecipe.recipe;
import static com.kwakmunsu.likelionprojectteam1.domain.view.entity.QView.view;
import static com.querydsl.core.types.Projections.constructor;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.MyPageOption;
import com.kwakmunsu.likelionprojectteam1.domain.member.entity.QMember;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipeInfinityPreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.BoardType;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.FoodType;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Occasion;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Purpose;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.dto.RecipePaginationDomainRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.SortOption;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.CommentResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeAuthorResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeBasicInfo;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeCountResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipePaginationResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeTagResponse;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.s3.endpoints.internal.Value.Str;

@RequiredArgsConstructor
@Repository
public class RecipeQueryDslRepository {

    private final JPAQueryFactory query;
    private static final int PAGE_SIZE = 15;

    public RecipeInfinityPreviewResponse findByMemberId(
            Long memberId,
            Long lastRecipeId,
            MyPageOption option
    ) {
        List<RecipePreviewResponse> responses = getSelectFromEntityAndJoin()
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

    public List<RecipePreviewResponse> findAllByPagination(RecipePaginationDomainRequest request) {
        int offset = getOffset(request.page());

        return getSelectFromEntityAndJoin()
                .where(
                        boardTypeEq(request.boardType()),
                        occasionEq(request.occasion()),
                        cookingTimeContain(request.cookingTime()),
                        purposeEq(request.purpose()),
                        foodTypeEq(request.foodType()),
                        ingredientContains(request.ingredient())
                )
                .orderBy(getOrderSpecifier(request.sortBy()))
                .groupBy(recipe.id)
                .offset(offset)
                .limit(PAGE_SIZE + 1)
                .fetch();
    }

    public RecipeDetailResponse findById(Long recipeId) {
        QMember commentAuthor = new QMember("commentAuthor");

        StringTemplate formattedCreatedAt = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})",
                recipe.createdAt,
                ConstantImpl.create("%y.%m.%d")
        );

        StringTemplate formattedCommentCreatedAt = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})",
                comment.createdAt,
                ConstantImpl.create("%y.%m.%d")
        );

        RecipeBasicInfo basicInfo = query
                .select(constructor(RecipeBasicInfo.class,
                        recipe.title,
                        recipe.introduction,
                        recipe.difficulty.stringValue(),
                        recipe.ingredients,
                        recipe.content,
                        formattedCreatedAt,
                        constructor(RecipeTagResponse.class,
                                recipe.tag.occasion.stringValue(),
                                recipe.tag.purpose.stringValue(),
                                recipe.tag.foodType.stringValue()
                        ),
                        constructor(RecipeAuthorResponse.class,
                                member.id,
                                member.nickname,
                                member.grade.stringValue()
                        )
                ))
                .from(recipe)
                .join(recipe.member, member)
                .where(recipe.id.eq(recipeId))
                .fetchOne();

        // 3. 이미지 목록 (1:N 관계)
        List<String> images = query
                .select(image.name)
                .from(image)
                .where(image.recipe.id.eq(recipeId))
                .fetch();

        RecipeCountResponse countResponse = query
                .select(constructor(RecipeCountResponse.class,
                        like.countDistinct(),
                        view.countDistinct(),
                        comment.countDistinct(),
                        favorites.countDistinct()
                ))
                .from(recipe)
                .leftJoin(like).on(recipe.id.eq(like.recipe.id))
                .leftJoin(view).on(recipe.id.eq(view.recipe.id))
                .leftJoin(comment).on(recipe.id.eq(comment.recipe.id))
                .leftJoin(favorites).on(recipe.id.eq(favorites.recipe.id))
                .where(recipe.id.eq(recipeId))
                .fetchOne();

        // 4. 댓글 목록 (1:N 관계)
        List<CommentResponse> commentResponses = query
                .select(constructor(CommentResponse.class,
                        comment.id,
                        comment.parentCommentId,
                        commentAuthor.id,
                        commentAuthor.nickname,
                        comment.content,
                        commentAuthor.grade.stringValue(),
                        formattedCommentCreatedAt
                ))
                .from(comment)
                .join(comment.member, commentAuthor)
                .where(comment.recipe.id.eq(recipeId))
                .fetch();

        return RecipeDetailResponse.from(images, basicInfo, countResponse, commentResponses);
    }

    public List<RecipePreviewResponse> findByTitle(String query, int page) {
        int offset = getOffset(page);

        return getSelectFromEntityAndJoin()
                .where(
                        recipe.title.contains(query)
                                .or(recipe.content.contains(query))
                )
                .orderBy(recipe.id.desc())
                .groupBy(recipe.id)
                .offset(offset)
                .limit(PAGE_SIZE + 1)
                .fetch();
    }

    private JPAQuery<RecipePreviewResponse> getSelectFromEntityAndJoin() {
        return query.select(constructor(
                        RecipePreviewResponse.class,
                        recipe.id.as("recipeId"),
                        image.name.min(),
                        recipe.title,
                        recipe.introduction,
                        recipe.difficulty.stringValue(),
                        constructor(RecipeTagResponse.class,
                                recipe.tag.occasion.stringValue(),
                                recipe.tag.purpose.stringValue(),
                                recipe.tag.foodType.stringValue()
                        ),
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
                ;
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

    private BooleanExpression boardTypeEq(String boardType) {
        if (boardType == null) {
            return null;
        }

        return recipe.boardType.eq(BoardType.valueOf(boardType));
    }
    private BooleanExpression occasionEq(String occasion) {
        if (occasion == null) {
            return null;
        }

        return recipe.tag.occasion.eq(Occasion.valueOf(occasion));
    }

    private BooleanExpression cookingTimeContain(Integer cookingTime) {
        if (cookingTime == null) {
            return null;
        }

        return recipe.cookingTime.between(cookingTime - 5, cookingTime + 5);
    }

    private BooleanExpression purposeEq(String purpose) {
        if (purpose == null) {
            return null;
        }

        return recipe.tag.purpose.eq(Purpose.valueOf(purpose));
    }

    private BooleanExpression foodTypeEq(String foodType) {
        if (foodType == null) {
            return null;
        }

        return recipe.tag.foodType.eq(FoodType.valueOf(foodType));
    }

    private BooleanExpression ingredientContains(String ingredient) {
        if (ingredient == null) {
            return null;
        }

        return recipe.ingredients.contains(ingredient);
    }

    private OrderSpecifier<?> getOrderSpecifier(String option) {
        SortOption sortOption;

        if (option == null) {
            sortOption = SortOption.ID_DESC;
        } else {
            sortOption = SortOption.valueOf(option);
        }

        return switch (sortOption) {
            case ID_DESC -> recipe.id.desc();
            case ID_ASC -> recipe.id.asc();
            case LIKE_DESC -> like.id.countDistinct().desc();
            case FAVORITES_DESC -> favorites.id.countDistinct().desc();
        };
    }


    private int getOffset(int page) {
        return (page - 1) * PAGE_SIZE;
    }
}