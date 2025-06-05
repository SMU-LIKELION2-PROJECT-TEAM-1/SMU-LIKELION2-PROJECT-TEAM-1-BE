package com.kwakmunsu.likelionprojectteam1.domain.ingredient.controller.dto.request;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.request.VoteCreateServiceRequest;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "투표 요청 DTO")
public record VoteCreateRequest(

        @Schema(description = "투표할 재료 id", example = "1")
        Long ingredientId,

        @Schema(description = "해당 챌린지 id", example = "1")
        Long challengeId
) {

    public VoteCreateServiceRequest toServiceRequest(Long memberId) {
        return VoteCreateServiceRequest.builder()
                .memberId(memberId)
                .ingredientId(ingredientId)
                .challengeId(challengeId)
                .build();
    }

}