package com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.dto;

import lombok.Builder;

@Builder
public record TagUpdateDomainRequest(
        String occasion,
        String foodType,
        String purpose
) {

}