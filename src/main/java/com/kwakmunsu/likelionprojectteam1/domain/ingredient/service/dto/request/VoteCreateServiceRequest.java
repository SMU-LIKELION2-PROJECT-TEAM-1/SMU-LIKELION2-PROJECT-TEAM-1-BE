package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.request;

import lombok.Builder;

@Builder
public record VoteCreateServiceRequest(Long memberId, Long ingredientId, Long challengeId) {

}