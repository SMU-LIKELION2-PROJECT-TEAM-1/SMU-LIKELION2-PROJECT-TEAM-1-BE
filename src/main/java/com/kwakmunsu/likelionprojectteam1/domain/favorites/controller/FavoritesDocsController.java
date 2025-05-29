package com.kwakmunsu.likelionprojectteam1.domain.favorites.controller;

import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Favorites Controller", description = "Favorites API")
public abstract class FavoritesDocsController {

    @Operation(summary = "레시피 찜 등록 또는 취소를 합니다. - JWT O")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "좋아요 등록 성공"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않은 레시피입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(
                    responseCode = "419",
                    description = "인증되지 않은 사용자입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)
                    )),
    })
    public abstract ResponseEntity<Void> favorites(
            Long memberId,
            @Parameter(description = "찜을 등록하거나 취소할 레시피의 ID", example = "123", required = true)
            Long recipeId
    );

}