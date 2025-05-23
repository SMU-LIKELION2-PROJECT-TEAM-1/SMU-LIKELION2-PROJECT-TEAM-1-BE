package com.kwakmunsu.likelionprojectteam1.domain.recipe.controller.dto;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.BoardType;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Difficulty;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.CookingTime;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.FoodType;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Purpose;
import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.TimeSituation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(name = "레시피 생성 요청 DTO")
@Builder
public record RecipeCreateRequest(

        @Schema(description = "게시판 종류 (CHALLENGE: 챌린지, DAILY: 데일리)", example = "CHALLENGE", requiredMode = REQUIRED)
        BoardType boardType,

        @Schema(description = "레시피 제목", example = "초간단 계란찜", requiredMode = REQUIRED)
        String title,

        @Schema(description = "요리 소개", example = "제가 제일 좋아하는 음식이에요!!", requiredMode = NOT_REQUIRED)
        String introduction,

        @Schema(description = "시간/상황 (아침, 점심, 저녁, 야식, 간식)", example = "아침", requiredMode = REQUIRED)
        String timeSituation,

        @Schema(description = "시간 (5분, 10분, 15~20분, 30분, 1시간)", example = "30분", requiredMode = REQUIRED)
        String cookingTime,

        @Schema(description = "목적 (다이어트, 벌크업, 건강식, 해장, 혼밥)", example = "혼밥", requiredMode = REQUIRED)
        String purpose,

        @Schema(description = "음식 종류 (한식, 양식, 일식, 중식)", example = "힌식", requiredMode = REQUIRED)
        String FoodType,

        @Schema(description = "난이도 (상, 중, 하)", example = "하", requiredMode = REQUIRED)
        String difficulty,

        @Schema(description = "요리 재료", example = "양파 1/2, 간장 2스푼", requiredMode = REQUIRED)
        String ingredients,

        @Schema(description = "조리법", example = "계란을 풀고 소금을 넣는다...등", requiredMode = REQUIRED)
        String content
) {

}