package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record RecipeBasicInfo(

        @Schema(description = "레시피 글 제목", example = "testTitle")
        String title,

        @Schema(description = "레시피 소개 글", example = "맛있는 음식입니다!")
        String introduction,

        @Schema(description = "레시피 난이도", example = "HIGH")
        String difficulty,

        @Schema(description = "레시피 재료", example = "양파 1/2개, 간장 2스푼..")
        String ingredient,

        @Schema(description = "레시피 내용", example = "레시피 내용이다...")
        String content,

        @Schema(description = "글 작성 일자", example = "25.03.22")
        String createdAt,

        RecipeTagResponse tagResponse,

        RecipeAuthorResponse authorResponse
) {

}