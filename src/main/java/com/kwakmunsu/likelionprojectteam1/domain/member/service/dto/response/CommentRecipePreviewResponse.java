package com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

@Schema(description = "내가 댓글 단 레시피 글 목록 DTO")
@Builder
public record CommentRecipePreviewResponse(

        @Schema(description = "레시피 글 목록")
        List<RecipePreviewResponse> responses
) {

}
