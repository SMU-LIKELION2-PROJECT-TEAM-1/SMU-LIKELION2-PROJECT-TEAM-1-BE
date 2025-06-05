package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipePaginationServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipeSearchServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipePaginationResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.WeeklyTop3RecipesResponse;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeQueryService {

    private final RecipeRepository recipeRepository;

    public RecipePaginationResponse getRecipes(RecipePaginationServiceRequest request) {
        return recipeRepository.findAllByPagination(request.toDomainRequest());
    }

    public RecipeDetailResponse getRecipe(Long recipeId) {
        return recipeRepository.getRecipe(recipeId);
    }

    public RecipePaginationResponse search(RecipeSearchServiceRequest request) {
        return recipeRepository.search(request.query(), request.page());
    }

    public WeeklyTop3RecipesResponse getLastWeekTop3Recipes() {
        LocalDateTime weekStart = getPreviousWeekMonDay();
        LocalDateTime weekEnd = getPreviousWeekMonDay().plusDays(6)
                .withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        log.info(weekStart.toString());
        log.info(weekEnd.toString());

        return recipeRepository.findTop3RecipesByLastWeekLikes(weekStart, weekEnd);
    }

    private LocalDateTime getPreviousWeekMonDay() {
        return LocalDateTime.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
//                .minusWeeks(1)  // 1주 전으로 이동
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

}