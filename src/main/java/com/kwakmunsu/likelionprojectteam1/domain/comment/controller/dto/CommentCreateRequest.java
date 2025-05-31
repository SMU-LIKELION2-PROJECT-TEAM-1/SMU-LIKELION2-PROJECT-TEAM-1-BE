package com.kwakmunsu.likelionprojectteam1.domain.comment.controller.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.request.CommentCreateServiceRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Schema(description = "댓글 생성 요청 DTO")
@Builder
public record CommentCreateRequest(

        @Schema(description = "댓글 내용", example = "정말 좋은 글이네요!", requiredMode = REQUIRED)
        @NotBlank(message = "내용을 입력해주세요.")
        String content,

        @Schema(description = "부모 댓글 id - 대댓글 작성 시 포함시킵니다.", example = "1", requiredMode = NOT_REQUIRED)
        Long parentCommentId
) {

    public CommentCreateServiceRequest toServiceRequest(Long memberId) {
        return CommentCreateServiceRequest.builder()
                .content(content)
                .parentCommentId(parentCommentId)
                .memberId(memberId)
                .build();
    }

}