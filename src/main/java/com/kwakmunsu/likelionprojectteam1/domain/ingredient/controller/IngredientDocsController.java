package com.kwakmunsu.likelionprojectteam1.domain.ingredient.controller;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.controller.dto.request.VoteCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.response.IngredientCandidateResponse;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public abstract class IngredientDocsController {

    @Operation(
            summary = "챌린지 재료 정보 조회 - JWT O",
            description = """
                    해당 주차의 챌린지 재료들을 조회합니다.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "챌린지 재료 정보 조회 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = IngredientCandidateResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<IngredientCandidateResponse> getChallengeCandidate();


    @Operation(
            summary = "챌린지 투표 - JWT O",
            description = """
                    해당 주차의 챌린지 재료 중 하나들 투표 합니다.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "챌린지 재료 투표 성공",
                    content = @Content(

                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Long.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<Long> vote(Long memberId, VoteCreateRequest request);

}