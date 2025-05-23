package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "레시피 좋아요, 조회수, 댓글 수, 찜 수 응답 DTO")
@Builder
public record RecipeCountResponse(

        @Schema(description = "레시피 좋아요 수", example = "150")
        long likeCount,

        @Schema(description = "레시피 조회수", example = "2000")
        long viewCount,

        @Schema(description = "레시피 댓글 개수", example = "5")
        long commentCount,

        @Schema(description = "레시피 찜 수", example = "12")
        long favoritesCount
) {

}
