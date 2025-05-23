package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "레시피 작성자 정보 응답 DTO")
@Builder
public record RecipeAuthorResponse(

        @Schema(description = "작성자 id", example = "123L")
        long authorId,

        @Schema(description = "작성자 닉네임", example = "testNickname")
        String nickname,

        @Schema(description = "작성자 등급", example = "GOLD")
        String grade
) {

}