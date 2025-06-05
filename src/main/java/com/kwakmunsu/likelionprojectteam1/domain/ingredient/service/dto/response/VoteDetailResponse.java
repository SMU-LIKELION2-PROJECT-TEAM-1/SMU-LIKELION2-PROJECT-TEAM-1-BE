package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "투표 응답 DTO")
@Builder
public record VoteDetailResponse(

        @Schema(description = "투표한 재료 id", example = "1")
        Long ingredientId,

        @Schema(description = "해당 재료 투표수", example = "14")
        Long count
) {

}