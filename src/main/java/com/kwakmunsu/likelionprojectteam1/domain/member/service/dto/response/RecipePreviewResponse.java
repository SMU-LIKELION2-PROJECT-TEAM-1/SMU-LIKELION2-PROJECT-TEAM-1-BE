package com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeAuthorResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeCountResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeTagResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RecipePreviewResponse(

        @Schema(description = "레시피 id", example = "1")
        Long id,

        @Schema(description = "https://example.com/image.jpg")
        String thumbnail,

        @Schema(description = "레시피 글 제목", example = "testTitle")
        String title,

        @Schema(description = "레시피 소개", example = "Easy and fluffy pancakes recipe.")
        String introduction,

        @Schema(description = "레시피 난이도", example = "HIGH")
        String difficulty,

        RecipeTagResponse tagResponse,

        RecipeAuthorResponse authorResponse,

        RecipeCountResponse countResponse
) {

    public static RecipePreviewResponse toTestMock() {
        return RecipePreviewResponse.builder()
                .thumbnail("https://example.com/image.jpg") // test Image 넣을 예정
                .title("testTitle")
                .introduction("Easy and fluffy pancakes recipe.")
                .difficulty("상")
                .authorResponse(RecipeAuthorResponse.builder()
                        .nickname("testNickname")
                        .id(12345L)
                        .grade("GOLD")
                        .build())
                .countResponse(RecipeCountResponse.builder()
                        .likeCount(150L)
                        .viewCount(2000L)
                        .commentCount(45L)
                        .favoritesCount(75L)
                        .build()
                )
                .build();
    }

}