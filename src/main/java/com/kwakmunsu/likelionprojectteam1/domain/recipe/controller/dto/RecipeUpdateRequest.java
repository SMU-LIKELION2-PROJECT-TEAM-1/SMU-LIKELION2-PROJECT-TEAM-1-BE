package com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.annotation.EnumValid;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.BoardType;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Difficulty;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.request.RecipeUpdateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.FoodType;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Occasion;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Purpose;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;

@Schema(description = "레시피 수정 요청 DTO")
@Builder
public record RecipeUpdateRequest(

        @Schema(description = "게시판 종류 (CHALLENGE: 챌린지, DAILY: 데일리)", example = "CHALLENGE", requiredMode = REQUIRED)
        @EnumValid(enumClass = BoardType.class, message = "CHALLENGE 또는 DAILY 를 정확히 입력해주세요.")
        String boardType,

        @Schema(description = "레시피 제목", example = "초간단 계란찜", requiredMode = REQUIRED)
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(max = 30, message = "최대 30글자까지 입력 가능합니다.")
        String title,

        @Schema(description = "요리 소개", example = "제가 제일 좋아하는 음식이에요!!", requiredMode = NOT_REQUIRED)
        @NotBlank(message = "레시피 소개 글을 입력해주세요.")
        @Size(max = 130, message = "최대 130글자까지 입력 가능합니다.")
        String introduction,

        @Schema(description = "시간/상황 (아침, 점심, 저녁, 야식, 간식)", example = "아침", requiredMode = REQUIRED)
        @EnumValid(enumClass = Occasion.class, message = "아침, 점심, 저녁, 야식, 간식 중에 하나를 입력해주세요.")
        String occasion,

        @Schema(description = "조리 시간", example = "30", requiredMode = REQUIRED)
        @Positive
        int cookingTime,

        @Schema(description = "목적 (다이어트 식단, 벌크업 식단, 건강식, 해장용, 혼밥용)", example = "혼밥", requiredMode = REQUIRED)
        @EnumValid(enumClass = Purpose.class, message = "다이어트 식단, 벌크업 식단, 건강식, 해장용, 혼밥용 중에 하나를 입력해주세요.")
        String purpose,

        @Schema(description = "음식 종류 (한식, 중식, 양식, 일식)", example = "힌식", requiredMode = REQUIRED)
        @EnumValid(enumClass = FoodType.class, message = "한식, 중식, 양식, 일식 중에 하나를 입력해주세요.")
        String foodType,

        @Schema(description = "난이도 (상, 중, 하)", example = "하", requiredMode = REQUIRED)
        @EnumValid(enumClass = Difficulty.class, message = "상, 중, 하 중에 하나를 입력해주세요.")
        String difficulty,

        @Schema(description = "요리 재료", example = "양파 1/2, 간장 2스푼", requiredMode = REQUIRED)
        @NotBlank(message = "레시피 재료를 입력해주세요.")
        @Size(max = 500, message = "최대 500글자까지 입력 가능합니다.")
        String ingredients,

        @Schema(description = "조리법", example = "계란을 풀고 소금을 넣는다...등", requiredMode = REQUIRED)
        @NotBlank(message = "레시피를 입력해주세요.")
        @Size(max = 500, message = "최대 500글자까지 입력 가능합니다.")
        String content,

        @Schema(description = "이미지 url", requiredMode = REQUIRED)
        @NotEmpty(message = "이미지 url를 입력해주세요.")
        List<String> imageUrls
) {
        public RecipeUpdateServiceRequest toServiceRequest(Long memberId) {
                return RecipeUpdateServiceRequest.builder()
                        .boardType(boardType)
                        .memberId(memberId)
                        .title(title)
                        .introduction(introduction)
                        .occasion(occasion)
                        .cookingTime(cookingTime)
                        .foodType(foodType)
                        .purpose(purpose)
                        .difficulty(difficulty)
                        .ingredients(ingredients)
                        .content(content)
                        .imageUrls(imageUrls)
                        .build();
        }

}