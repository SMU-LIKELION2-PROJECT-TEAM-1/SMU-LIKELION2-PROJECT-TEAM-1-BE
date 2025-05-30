package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Tag 응답 DTO")
@Builder
public record RecipeTagResponse(

        @Schema(description = "시간/상황", example = "LUNCH")
        String occasion,

        @Schema(description = "목적", example = "SOLO_MEAL")
        String purpose,

        @Schema(description = "음식 유형", example = "KOREAN")
        String foodType
) {

}