package com.kwakmunsu.likelionprojectteam1.domain.recipe.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwakmunsu.likelionprojectteam1.TestSecurityConfig;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto.RecipeCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto.RecipeUpdateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.RecipeCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.RecipeQueryService;
import com.kwakmunsu.likelionprojectteam1.global.annotation.TestMember;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@Import(TestSecurityConfig.class)
@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    public static final String BASE_URL = "/recipes";

    @MockBean
    private RecipeCommandService recipeCommandService;

    @MockBean
    private RecipeQueryService recipeQueryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("레시피를 생성한다.")
    @TestMember
    @Test
    void createRecipe() throws Exception {
        // given
        Long testRecipeId = 1L;
        RecipeCreateRequest request = getRecipeCreateRequest();
        given(recipeCommandService.create(any(), any())).willReturn(testRecipeId);
        MockMultipartFile recipePart = new MockMultipartFile(
                "recipe",
                null,
                "application/json",
                objectMapper.writeValueAsBytes(request)
        );
        MockMultipartFile imagePart = new MockMultipartFile(
                "images",
                "test-image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "fake image content".getBytes(StandardCharsets.UTF_8)
        );

        // expected
        mockMvc.perform(
                        multipart(BASE_URL)
                                .file(recipePart)
                                .file(imagePart)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/recipes/" + testRecipeId));
    }

    @DisplayName("레시피를 수정한다.")
    @TestMember
    @Test
    void updateRecipe() throws Exception {
        // given
        Long recipeId = 1L;
        RecipeUpdateRequest request = getRecipeUpdateRequest();
        MockMultipartFile imagePart = new MockMultipartFile(
                "images",
                "test-image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "fake image content".getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile recipePart = new MockMultipartFile(
                "request",
                null,
                "application/json",
                objectMapper.writeValueAsBytes(request)
        );
        // expected
        mockMvc.perform(
                        multipart(BASE_URL + "/{recipeId}", recipeId)
                                .file(recipePart)
                                .file(imagePart)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .content(objectMapper.writeValueAsString(request))
                                .with(request1 -> {
                                    request1.setMethod("PATCH");
                                    return request1;
                                })
                )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @DisplayName("레시피 생성 요청 값 유효성 테스트")
    @TestMember
    @ParameterizedTest
    @CsvSource({
            // boardType, title, introduction, occasion, cookingTime, purpose, foodType, difficulty, ingredients, content, expectedMessage
            ",초간단 계란찜, 소개, 아침, 30, 혼밥, 한식, 하, 재료, 조리법,CHALLENGE 또는 DAILY 를 정확히 입력해주세요.",
            "CHALLENGE, , 소개, 아침, 30, 혼밥, 한식, 하, 재료, 조리법, 제목을 입력해주세요.",
            "CHALLENGE, 초간단 계란찜, , 아침, 30, 혼밥, 한식, 하, 재료,조리법, 레시피 소개 글을 입력해주세요.",
            "CHALLENGE, 초간단 계란찜, 소개, , 30, 혼밥, 한식, 하, 재료, 조리법, '아침, 점심, 저녁, 야식, 간식 중에 하나를 입력해주세요.'",
            "CHALLENGE, 초간단 계란찜, 소개, 새벽, 30, 혼밥, 한식, 하, 재료, 조리법, '아침, 점심, 저녁, 야식, 간식 중에 하나를 입력해주세요.'",
            "CHALLENGE, 초간단 계란찜, 소개, 아침, -1, 혼밥, 한식, 하, 재료,조리법,'0보다 커야합니다.'",
            "CHALLENGE, 초간단 계란찜, 소개, 아침, 30, , 한식, 하, 재료, 조리법,'다이어트 식단, 벌크업 식단, 건강식, 해장용, 혼밥용 중에 하나를 입력해주세요.'",
            "CHALLENGE, 초간단 계란찜, 소개, 아침, 30, 혼밥, , 하, 재료,조리법, '한식, 중식, 양식, 일식 중에 하나를 입력해주세요.'",
            "CHALLENGE, 초간단 계란찜, 소개, 아침, 30, 혼밥, 한식, ,재료,조리법, '상, 중, 하 중에 하나를 입력해주세요.'",
            "CHALLENGE, 초간단 계란찜, 소개, 아침, 30, 혼밥, 한식, 하, ,조리법, '레시피 재료를 입력해주세요.'",
            "CHALLENGE, 초간단 계란찜, 소개, 아침, 30, 혼밥, 한식, 하, 재료, , '레시피를 입력해주세요.'"
    })
    void shouldFailValidationRecipeCreate(
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
            String expectedMessage
    ) throws Exception {
        // given
        RecipeCreateRequest request = RecipeCreateRequest.builder()
                .boardType(boardType)
                .title(title)
                .introduction(introduction)
                .occasion(occasion)
                .cookingTime(cookingTime)
                .purpose(purpose)
                .foodType(foodType)
                .difficulty(difficulty)
                .ingredients(ingredients)
                .content(content)
                .build();

        MockMultipartFile recipePart = new MockMultipartFile(
                "recipe",
                null,
                "application/json",
                objectMapper.writeValueAsBytes(request)
        );

        // expected
        mockMvc.perform(
                        multipart(BASE_URL)
                                .file(recipePart)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validation.*").value(org.hamcrest.Matchers.hasItem(expectedMessage)));
    }

    private RecipeCreateRequest getRecipeCreateRequest() {
        return RecipeCreateRequest.builder()
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
                .build();
    }

    private RecipeUpdateRequest getRecipeUpdateRequest() {
        return RecipeUpdateRequest.builder()
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
                .imageUrls(List.of("1,2"))
                .build();
    }

}