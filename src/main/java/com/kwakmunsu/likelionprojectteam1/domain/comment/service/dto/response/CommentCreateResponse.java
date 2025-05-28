package com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.response;

import com.kwakmunsu.likelionprojectteam1.global.util.TimeConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;

@Schema(description = "댓글 생성 응답 DTO")
@Builder
public record CommentCreateResponse(

        @Schema(description = "댓글 id", example = "1")
        Long id,

        @Schema(description = "루트 댓글이면 null, 대댓글이면 부모 댓글 id", example = "null or 1")
        Long parentCommentId,

        @Schema(description = "댓글 내용", example = "글이 너무 좋아요~")
        String content,

        @Schema(description = "댓글 생성 시간", example = "25.05.29")
        String createdAt
) {

    public static CommentCreateResponse from(
            Long id,
            Long parentCommentId,
            String content,
            LocalDateTime createdAt
    ) {
        return CommentCreateResponse.builder()
                .id(id)
                .parentCommentId(parentCommentId)
                .content(content)
                .createdAt(TimeConverter.datetimeToString(createdAt))
                .build();
    }

}