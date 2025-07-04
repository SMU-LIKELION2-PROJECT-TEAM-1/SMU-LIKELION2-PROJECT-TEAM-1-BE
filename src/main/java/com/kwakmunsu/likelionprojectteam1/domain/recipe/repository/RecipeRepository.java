package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.MyPageOption;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipeInfinityPreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.dto.RecipePaginationDomainRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipePaginationResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.WeeklyTop3RecipesResponse;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import com.kwakmunsu.likelionprojectteam1.global.exception.UnAuthenticationException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class RecipeRepository {

    private final RecipeJpaRepository recipeJpaRepository;
    private final RecipeQueryDslRepository recipeQueryDslRepository;

    public Recipe save(Recipe recipe) {
        return recipeJpaRepository.save(recipe);
    }

    public Recipe findById(Long id) {
        return recipeJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_RECIPE.getMessage()));
    }

    public Recipe findByIdAndMemberId(Long id, Long memberId) {
        return recipeJpaRepository.findByIdAndMemberId(id, memberId)
                .orElseThrow(() -> new UnAuthenticationException(ErrorMessage.MODIFY_UNAUTHORIZED_RECIPE.getMessage()));
    }

    public boolean existsByIdAndMemberId(Long id, Long memberId) {
        return recipeJpaRepository.existsByIdAndMemberId(id, memberId);
    }

    public void deleteById(Long id) {
        recipeJpaRepository.deleteById(id);
    }

    public RecipeInfinityPreviewResponse findByMemberId(Long memberId, Long lastRecipeId, MyPageOption option) {
        return recipeQueryDslRepository.findByMemberId(memberId, lastRecipeId, option);
    }

    public boolean existsById(Long id) {
        return recipeJpaRepository.existsById(id);
    }

    public RecipePaginationResponse findAllByPagination(RecipePaginationDomainRequest request) {
        List<RecipePreviewResponse> previewResponses = recipeQueryDslRepository.findAllByPagination(request);

        return RecipePaginationResponse.builder()
                .responses(previewResponses)
                .build();
    }

    // TODO: 네이밍 고민
    public RecipeDetailResponse getRecipe(Long recipeId) {
        return recipeQueryDslRepository.findById(recipeId);
    }

    public RecipePaginationResponse search(String query, int page) {
        List<RecipePreviewResponse> previewResponses = recipeQueryDslRepository.findByTitle(query, page);

        return RecipePaginationResponse.builder()
                .responses(previewResponses)
                .build();
    }

    public WeeklyTop3RecipesResponse findTop3RecipesByLastWeekLikes(LocalDateTime lastWeekStart, LocalDateTime lastWeekEnd) {
        List<RecipePreviewResponse> responses = recipeQueryDslRepository.findTop3RecipesByLastWeekLikes(lastWeekStart, lastWeekEnd);

        return WeeklyTop3RecipesResponse.builder()
                .responses(responses)
                .build();
    }

}