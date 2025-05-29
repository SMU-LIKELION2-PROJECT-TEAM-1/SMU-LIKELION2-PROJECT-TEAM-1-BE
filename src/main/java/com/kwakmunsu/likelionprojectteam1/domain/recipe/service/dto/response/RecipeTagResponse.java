package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Tag 응답 DTO")
@Builder
public record RecipeTagResponse(

        @Schema(description = "시간/상황", example = "아침")
        String occasion,

        @Schema(description = "조리 시간", example = "10분")
        String cookingTime,

        @Schema(description = "목적", example = "혼밥")
        String purpose,

        @Schema(description = "음식 유형", example = "한식")
        String foodType
) {

}