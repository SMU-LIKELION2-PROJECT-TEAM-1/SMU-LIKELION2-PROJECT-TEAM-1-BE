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

    @Operation(
            summary = "레시피 찜 등록 - JWT O",
            description = """
                     특정 레시피에 찜를 등록합니다. 이미 좋아요가 등록된 경우 409를 반환합니다.
                    """
    )
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
                    responseCode = "409",
                    description = "이미 찜이 등록된 레시피입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)
                    )),
    })
    public abstract ResponseEntity<Void> favorites(
            @Parameter(description = "찜을 등록할 레시피의 ID", example = "123", required = true)
            Long recipeId
    );

    @Operation(
            summary = "레시피 찜 취소 - JWT O",
            description = "특정 레시피에 등록된 찜을 취소합니다. 찜이 등록되어 있지 않으면 409를 반환합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "찜 취소 성공"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않은 레시피입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "찜이 등록되지 않은 레시피입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)
                    )),
    })
    public abstract ResponseEntity<Void> unFavorites(
            @Parameter(description = "찜을 취소할 레시피의 ID", example = "123", required = true)
            Long recipeId
    );

}