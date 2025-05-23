package com.kwakmunsu.likelionprojectteam1.domain.comment.controller.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "댓글 생성 요청 DTO")
@Builder
public record CommentCreateRequest(

        @Schema(description = "댓글 내용", example = "정말 좋은 글이네요!", requiredMode = REQUIRED)
        String content
) {

}