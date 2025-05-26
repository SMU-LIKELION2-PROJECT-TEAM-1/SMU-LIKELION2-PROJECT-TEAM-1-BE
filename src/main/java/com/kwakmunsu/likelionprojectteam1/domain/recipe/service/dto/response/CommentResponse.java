package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record CommentResponse(

        @Schema(description = "댓글 id", example = "1")
        Long id,

        @Schema(description = "작성자 id", example = "1")
        Long authorId,

        @Schema(description = "작성자 닉네임", example = "testNickname")
        String nickname,

        @Schema(description = "작성자 등급", example = "GOLD")
        String grade,

        @Schema(description = "댓글", example = "testComment")
        String content,

        @Schema(description = "댓글 생성 날짜", example = "2025.01.24")
        String createdAt
) {

}