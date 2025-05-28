package com.kwakmunsu.likelionprojectteam1.domain.recipe.controller;

import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.RecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto.RecipeCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto.RecipeUpdateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.RecipeCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipeDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.RecipePaginationResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response.WeeklyTop3RecipesResponse;
import com.kwakmunsu.likelionprojectteam1.global.annotation.AuthMember;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/recipes")
@RequiredArgsConstructor
@RestController
public class RecipeController extends RecipeDocsController {

    private final RecipeCommandService recipeCommandService;

    // FIXME: image 가 스웨거에 안라감
    @Override
    @PostMapping
    public ResponseEntity<Void> create(
            @AuthMember Long memberId,
            @Valid @RequestPart(value = "recipe") RecipeCreateRequest request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        Long recipeId = recipeCommandService.create(request.toServiceRequest(memberId), images);
        URI uri = URI.create("/recipes/" + recipeId);

        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<RecipePaginationResponse> getRecipes(
            @RequestParam(value = "boardType", required = false) String boardType,
            @RequestParam(value = "timeSituation", required = false) String timeSituation,
            @RequestParam(value = "cookingTime", required = false) String cookingTime,
            @RequestParam(value = "purpose", required = false) String purpose,
            @RequestParam(value = "foodType", required = false) String foodType,
            @RequestParam(value = "sortBy", defaultValue = "createAt", required = false) String sortBy,
            @RequestParam(value = "ingredient", required = false) String ingredient,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDetailResponse> getRecipe(@PathVariable Long recipeId) {
        return ResponseEntity.ok(RecipeDetailResponse.builder().build());
    }

    @Override
    @PostMapping("/search")
    public ResponseEntity<RecipePaginationResponse> search(
            @RequestParam(value = "query") String query,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(RecipePaginationResponse.builder()
                .responses(getTestRecipePreviewResponse())
                .build()
        );
    }

    @Override
    @PostMapping("/weekly")
    public ResponseEntity<WeeklyTop3RecipesResponse> weekly() {
        return ResponseEntity.ok(WeeklyTop3RecipesResponse.builder()
                .responses(getTestRecipePreviewResponse())
                .build()
        );
    }

    @Override
    @PatchMapping("/{recipeId}")
    public ResponseEntity<Void> update(
            @AuthMember Long memberId,
            @PathVariable Long recipeId,
            @Valid @RequestPart(value = "recipe") RecipeUpdateRequest request,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        recipeCommandService.update(request.toServiceRequest(memberId), recipeId, images);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> delete(
            @AuthMember Long memberId,
            @PathVariable Long recipeId
    ) {
        recipeCommandService.delete(memberId, recipeId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{ingredientId}/vote")
    public ResponseEntity<Void> vote(@PathVariable Long ingredientId) {
        return ResponseEntity.ok().build();
    }

    // 추후 삭제 예정
    private static List<RecipePreviewResponse> getTestRecipePreviewResponse() {
        List<RecipePreviewResponse> responses = new ArrayList<>();
        RecipePreviewResponse testMock = RecipePreviewResponse.toTestMock();
        responses.add(testMock);
        responses.add(testMock);
        return responses;
    }

}