package com.kwakmunsu.likelionprojectteam1.domain.recipe.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

@Builder
public record RecipeDetailResponse(

        @Schema(description = "음식 사진들")
        List<String> images,

        @Schema(description = "recipe 기본 정보")
        RecipeBasicInfo basicInfo,

        RecipeCountResponse countResponse,

        @Schema(description = "댓글 목록")
        List<CommentResponse> commentResponses
) {

    public static RecipeDetailResponse from(
            List<String> images,
            RecipeBasicInfo basicInfo,
            RecipeCountResponse countResponse,
            List<CommentResponse> commentResponses
    ) {
        return RecipeDetailResponse.builder()
                .images(images)
                .basicInfo(basicInfo)
                .countResponse(countResponse)
                .commentResponses(commentResponses)
                .build();
    }

    public static RecipeDetailResponse mock() {
        return RecipeDetailResponse.builder()
                .images(List.of(
                        "https://like-lion2.s3.ap-northeast-2.amazonaws.com/recipe/c1ac8831-cdec-41dd-85c1-13a8912f2e5d-IMG_4088.PNG"
                ))
                .basicInfo(RecipeBasicInfo.builder()
                        .title("김치볶음밥")
                        .introduction("간단하고 맛있는 김치볶음밥 레시피입니다.")
                        .difficulty("HIGH")
                        .ingredient("김치 100g, 밥 1공기, 대파 1/2대, 계란 1개, 참기름 1스푼")
                        .content(
                                "1. 팬에 기름을 두르고 대파를 볶아 파기름을 낸다.\n2. 김치를 넣고 볶다가 밥을 넣고 함께 볶는다.\n3. 간장, 참기름으로 간을 맞추고 계란 프라이를 올려 완성한다.")
                        .createdAt("25.03.22")
                        .tagResponse(RecipeTagResponse.builder()
                                .occasion("LUNCH")
                                .purpose("SOLO_MEAL")
                                .foodType("KOREAN")
                                .build())
                        .authorResponse(RecipeAuthorResponse.builder()
                                .id(1L)
                                .nickname("likelion_dev")
                                .grade("FLAME_CHEF")
                                .build())
                        .build()
                )
                .countResponse(RecipeCountResponse.builder()
                        .likeCount(1L)
                        .commentCount(1L)
                        .favoritesCount(1L)
                        .viewCount(1L)
                        .build()
                )
                .commentResponses(List.of(
                        CommentResponse.builder()
                                .id(101L)
                                .authorId(1L)
                                .nickname("testNickname")
                                .content("정말 맛있어요!")
                                .grade("FLAME_CHEF")
                                .createdAt("25.05.30")
                                .build(),
                        CommentResponse.builder()
                                .id(102L)
                                .authorId(2L)
                                .nickname("testNickname2")
                                .grade("FLAME_CHEF")
                                .content("정말 맛있어요!")
                                .createdAt("25.05.30")
                                .build()
                ))
                .build();
    }

}