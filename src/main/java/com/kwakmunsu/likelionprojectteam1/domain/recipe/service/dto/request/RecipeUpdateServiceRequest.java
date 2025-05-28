package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.dto.RecipeUpdateDomainRequest;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.dto.TagUpdateDomainRequest;
import java.util.List;
import lombok.Builder;

@Builder
public record RecipeUpdateServiceRequest(
        Long memberId,
        String boardType,
        String title,
        String introduction,
        String occasion,
        int cookingTime,
        String purpose,
        String foodType,
        String difficulty,
        String ingredients,
        String content,
        List<String> imageUrls
) {

    public RecipeUpdateDomainRequest toRecipeDomainRequest() {
        return RecipeUpdateDomainRequest.builder()
                .boardType(boardType)
                .title(title)
                .introduction(introduction)
                .cookingTime(cookingTime)
                .ingredients(ingredients)
                .difficulty(difficulty)
                .content(content)
                .build();
    }

    public TagUpdateDomainRequest toTagDomainRequest() {
        return TagUpdateDomainRequest.builder()
                .occasion(occasion)
                .purpose(purpose)
                .foodType(foodType)
                .build();
    }

}