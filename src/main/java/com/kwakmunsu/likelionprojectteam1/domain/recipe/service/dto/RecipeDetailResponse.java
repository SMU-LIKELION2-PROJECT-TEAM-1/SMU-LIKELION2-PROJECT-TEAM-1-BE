package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

@Builder
public record RecipeDetailResponse(

        @Schema(description = "음식 사진들")
        List<String> images,

        @Schema(description = "레시피 글 제목", example = "testTitle")
        String title,

        @Schema(description = "레시피 소개 글", example = "맛있는 음식입니다!")
        String introduction,

        @Schema(description = "레시피 난이도", example = "상")
        String difficulty,

        @Schema(description = "레시피 재료", example = "양파 1/2개, 간장 2스푼..")
        String ingredient,

        @Schema(description = "레시피 내용", example = "레시피 내용이다...")
        String content,

        RecipeTagResponse tagResponse,

        RecipeAuthorResponse authorResponse,

        RecipeCountResponse countResponse,

        @Schema(description = "댓글 목록")
        List<CommentResponse> commentResponses
) {

}