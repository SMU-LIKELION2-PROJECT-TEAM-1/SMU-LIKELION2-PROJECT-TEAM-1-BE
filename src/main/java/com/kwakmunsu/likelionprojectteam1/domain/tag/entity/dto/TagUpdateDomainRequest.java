package com.kwakmunsu.likelionprojectteam1.domain.tag.entity.dto;

import lombok.Builder;

@Builder
public record TagUpdateDomainRequest(
        String occasion,
        String foodType,
        String purpose
) {

}