package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import com.kwakmunsu.likelionprojectteam1.domain.image.ImageCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.BoardType;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Difficulty;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipeCreateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipeUpdateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.FoodType;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Occasion;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Purpose;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Tag;
import com.kwakmunsu.likelionprojectteam1.domain.tag.repository.TagRepository;
import com.kwakmunsu.likelionprojectteam1.global.exception.UnAuthenticationException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class RecipeCommandService {

    private final RecipeRepository recipeRepository;
    private final ImageCommandService imageCommandService;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final RecipeDeleteService recipeDeleteService;

    // TODO: 챌린지 일 경우 유효성 체크 해야함 챌린지 재료가 포함되어 있어야함.
    @Transactional
    public Long create(RecipeCreateServiceRequest request, List<MultipartFile> images) {
        Member member = memberRepository.findById(request.memberId());

        Recipe recipe = createRecipe(request, member);
        Recipe savedRecipe = recipeRepository.save(recipe);

        Tag tag = createTag(request, savedRecipe);

        tagRepository.save(tag);
        imageCommandService.upload(images, savedRecipe);

        return savedRecipe.getId();
    }

    @Transactional
    public void update(
            RecipeUpdateServiceRequest request,
            Long recipeId,
            List<MultipartFile> files
    ) {
        Recipe recipe = recipeRepository.findByIdAndMemberId(recipeId, request.memberId());
        Tag tag = tagRepository.findByRecipeId(recipeId);

        imageCommandService.deleteByUrls(request.imageUrls());
        imageCommandService.upload(files, recipe);

        recipe.updateRecipe(request.toRecipeDomainRequest());
        tag.updateTag(request.toTagDomainRequest());
    }

    public void delete(Long recipeId, Long memberId) {
        if (recipeRepository.existsByIdAndMemberId(recipeId, memberId)) {
            recipeDeleteService.deleteByRecipeId(recipeId);
            return;
        }
        throw new UnAuthenticationException(ErrorMessage.DELETE_UNAUTHORIZED_RECIPE.getMessage());
    }

    private Tag createTag(RecipeCreateServiceRequest request, Recipe recipe) {
        return Tag.builder()
                .recipe(recipe)
                .occasion(Occasion.valueOf(request.occasion()))
                .purpose(Purpose.valueOf(request.purpose()))
                .foodType(FoodType.valueOf(request.foodType()))
                .build();
    }

    private Recipe createRecipe(RecipeCreateServiceRequest request, Member member) {
        return Recipe.builder()
                .boardType(BoardType.valueOf(request.boardType()))
                .title(request.title())
                .member(member)
                .cookingTime(request.cookingTime())
                .difficulty(Difficulty.valueOf(request.difficulty()))
                .ingredients(request.ingredients())
                .introduction(request.introduction())
                .content(request.content())
                .build();
    }

}