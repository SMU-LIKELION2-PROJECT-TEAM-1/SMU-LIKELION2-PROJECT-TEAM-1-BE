package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto;

import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.RecipePreviewResponse;
import java.util.List;
import lombok.Builder;

@Builder
public record RecipePaginationResponse(List<RecipePreviewResponse> responses) {

}