package com.kwakmunsu.likelionprojectteam1.domain.comment.controller.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.request.CommentUpdateServiceRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Schema(description = "댓글 생성 요청 DTO")
@Builder
public record CommentUpdateRequest(

        @Schema(description = "댓글 내용", example = "정말 좋은 글이네요!", requiredMode = REQUIRED)
        @NotBlank(message = "내용을 입력해주세요.")
        String content

) {

    public CommentUpdateServiceRequest toServiceRequest(Long memberId) {
        return CommentUpdateServiceRequest.builder()
                .content(content)
                .memberId(memberId)
                .build();
    }

}