package com.kwakmunsu.likelionprojectteam1.domain.like.controller;

import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Like Controller", description = "Like API")
public abstract class LikeDocsController {

    @Operation(
            summary = "레시피 좋아요 등록 및 취소 - JWT O",
            description = """
                     특정 레시피에 좋아요를 등록하거나 취소 합니다.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "좋아요 등록 또는 취소 성공"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "존재하지 않은 레시피입니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)
                    )),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자입니다..",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)
                    )),
    })
    public abstract ResponseEntity<Void> like(
            Long memberId,
            @Parameter(description = "좋아요를 등록하거나 취소할 레시피의 ID", example = "123", required = true)
            Long recipeId
    );

}