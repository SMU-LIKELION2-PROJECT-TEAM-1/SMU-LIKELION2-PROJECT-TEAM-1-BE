package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.kwakmunsu.likelionprojectteam1.domain.image.service.ImageCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.BoardType;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Difficulty;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipeCreateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipeUpdateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.global.exception.BadRequestException;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import com.kwakmunsu.likelionprojectteam1.global.exception.UnAuthenticationException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class RecipeCommandServiceTest {

    @Mock
    private RecipeDeleteService recipeDeleteService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ImageCommandService imageCommandService;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private RecipeCommandService recipeCommandService;

    @DisplayName("레시피를 생성한다.")
    @Test
    void createRecipe() {
        // given
        Long memberId = 1L;
        Member member = mock(Member.class);
        given(memberRepository.findById(memberId)).willReturn(member);

        Recipe savedRecipe = mock(Recipe.class);
        given(recipeRepository.save(any(Recipe.class))).willReturn(savedRecipe);
        given(savedRecipe.getId()).willReturn(100L);

        RecipeCreateServiceRequest request = getRecipeCreateServiceRequest(memberId);
        List<MultipartFile> images = List.of(mock(MultipartFile.class));

        // when
        Long resultId = recipeCommandService.create(request, images);

        // then
        assertThat(resultId).isEqualTo(100L);
        verify(memberRepository).findById(memberId);
        verify(recipeRepository).save(any(Recipe.class));
        verify(imageCommandService).upload(images, savedRecipe);
    }

    @DisplayName("레시피를 생성 시 회원을 찾을 수 없다면 예외를 반환한다.")
    @Test
    void throwsExceptionWhenNotExistsMember() {
        // given
        Long memberId = 1L;
        given(memberRepository.findById(memberId))
                .willThrow(new NotFoundException(ErrorMessage.NOT_FOUND_MEMBER.getMessage()));

        RecipeCreateServiceRequest request = getRecipeCreateServiceRequest(memberId);
        List<MultipartFile> images = List.of(mock(MultipartFile.class));

        // when & then
        assertThatThrownBy(() -> recipeCommandService.create(request, images))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ErrorMessage.NOT_FOUND_MEMBER.getMessage());
    }

    @DisplayName("레시피를 생성 할 때 이미지 업로드 실패 시 예외를 반환한다.")
    @Test
    void throwsExceptionWhenFailUploadImage() {
        // given
        Long memberId = 1L;

        RecipeCreateServiceRequest request = getRecipeCreateServiceRequest(memberId);
        List<MultipartFile> images = List.of(mock(MultipartFile.class));

        willThrow(new BadRequestException(ErrorMessage.INVALID_FILE_EXTENSION.getMessage()))
                .given(imageCommandService).upload(any(), any());

        // when & then
        assertThatThrownBy(() -> recipeCommandService.create(request, images))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(ErrorMessage.INVALID_FILE_EXTENSION.getMessage());
    }

    @DisplayName("레시피를 수정한다.")
    @Test
    void recipeUpdate() {
        // given
        Long memberId = 1L;
        RecipeUpdateServiceRequest request = getRecipeUpdateServiceRequest(memberId);
        List<MultipartFile> list = new ArrayList<>();
        Recipe recipe = getRecipe();
        given(recipeRepository.findByIdAndMemberId(any(Long.class), any(Long.class))).willReturn(recipe);

        // when
        recipeCommandService.update(request, 1L, list);

        // then
        verify(recipeRepository).findByIdAndMemberId(1L, 1L);
        verify(imageCommandService).deleteByUrls(request.imageUrls());
        verify(imageCommandService).upload(list, recipe);
        assertThat(recipe.getContent()).isEqualTo("update-content");
        assertThat(recipe.getIngredients()).isEqualTo("update-ingredient1, update-ingredient2");
        assertThat(recipe.getBoardType()).isEqualTo(BoardType.DAILY);
    }

    @DisplayName("수정 요청 시 작성자가 아닐 경우 예외를 반환한다.")
    @Test
    void throwsExceptionWhenMemberHasNoUpdatePermission() {
        // given
        RecipeUpdateServiceRequest request = getRecipeUpdateServiceRequest(1L);
        List<MultipartFile> list = new ArrayList<>();
        given(recipeRepository.findByIdAndMemberId(any(Long.class), any(Long.class)))
                .willThrow(new UnAuthenticationException(ErrorMessage.MODIFY_UNAUTHORIZED_RECIPE.getMessage()));

        // when & then
        assertThatThrownBy(() -> recipeCommandService.update(request, 1L, list))
                .isInstanceOf(UnAuthenticationException.class)
                .hasMessage(ErrorMessage.MODIFY_UNAUTHORIZED_RECIPE.getMessage());
    }

    @DisplayName("레시피를 삭제한다")
    @Test
    void deleteRecipe() {
        // given
        Long recipeId = 1L;
        Long memberId = 1L;
        given(recipeRepository.existsByIdAndMemberId(any(Long.class), any(Long.class))).willReturn(true);

        // when
        recipeCommandService.delete(recipeId, memberId);

        // then
        verify(recipeDeleteService).deleteByRecipeId(recipeId);
    }

    @DisplayName("삭제 권한이 없을 경우 예외를 반환한다.")
    @Test
    void throwsExceptionWhenMemberHasNoDeletePermission() {
        // given
        Long recipeId = 1L;
        Long memberId = 1L;
        given(recipeRepository.existsByIdAndMemberId(any(Long.class), any(Long.class))).willReturn(false);

        // when & then
        assertThatThrownBy(() -> recipeCommandService.delete(recipeId, memberId))
                .isInstanceOf(UnAuthenticationException.class)
                .hasMessage(ErrorMessage.DELETE_UNAUTHORIZED_RECIPE.getMessage());
    }

    private RecipeCreateServiceRequest getRecipeCreateServiceRequest(Long memberId) {
        return RecipeCreateServiceRequest.builder()
                .memberId(memberId)
                .boardType("DAILY")
                .title("제목")
                .cookingTime(10)
                .difficulty("HIGH")
                .ingredients("재료1, 재료2")
                .introduction("소개")
                .content("내용")
                .occasion("BREAKFAST")
                .foodType("KOREAN")
                .purpose("BULK_UP")
                .build();
    }

    private RecipeUpdateServiceRequest getRecipeUpdateServiceRequest(Long memberId) {
        return RecipeUpdateServiceRequest.builder()
                .memberId(memberId)
                .boardType("DAILY")
                .title("update-title")
                .cookingTime(10)
                .difficulty("HIGH")
                .ingredients("update-ingredient1, update-ingredient2")
                .introduction("소개")
                .content("update-content")
                .occasion("BREAKFAST")
                .foodType("KOREAN")
                .purpose("BULK_UP")
                .imageUrls(List.of("test1", "test2"))
                .build();
    }

    private Recipe getRecipe() {
        return Recipe.builder()
                .title("test-title")
                .introduction("test-introduction")
                .cookingTime(10)
                .content("test-content")
                .ingredients("test-ingredients")
                .content("test-content")
                .boardType(BoardType.CHALLENGE)
                .difficulty(Difficulty.LOW)
                .build();
    }

}