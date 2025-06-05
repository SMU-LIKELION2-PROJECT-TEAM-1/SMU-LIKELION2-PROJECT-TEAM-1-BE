package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "챌린지 재료 응답 DTO")
@Builder
public record IngredientDetailResponse(

        @Schema(description = "재료 id", example = "1")
        Long id,

        @Schema(description = "챌린지 id", example = "1")
        Long challengeId,

        @Schema(description = "재료 이름", example = "양파")
        String title,

        @Schema(description = "재료 설명", example = "이 내용은......")
        String description,

        @Schema(description = "투표수", example = "12")
        Long count
) {

}