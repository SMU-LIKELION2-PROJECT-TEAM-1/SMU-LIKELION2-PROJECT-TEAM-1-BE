package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response;

import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipePreviewResponse;
import java.util.List;
import lombok.Builder;

@Builder
public record WeeklyTop3RecipesResponse(List<RecipePreviewResponse> responses) {

}