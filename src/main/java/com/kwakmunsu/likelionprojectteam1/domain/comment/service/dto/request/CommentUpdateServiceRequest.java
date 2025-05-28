package com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.request;

import lombok.Builder;

@Builder
public record CommentUpdateServiceRequest(
        String content,
        Long memberId
) {

}