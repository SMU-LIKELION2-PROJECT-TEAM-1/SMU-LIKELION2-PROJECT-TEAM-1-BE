package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto;

import lombok.Builder;

@Builder
public record IngredientDetailResponse(String title, String description) {

}