package com.kwakmunsu.likelionprojectteam1.domain.member.controller;

import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.CommentRecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.FavoritesRecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.LikeRecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.MemberInfoResponse;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Member Controller", description = "Member API")
public abstract class MemberDocsController {

    @Operation(
            summary = "내 정보 조회 - JWT O",
            description = "내 정보(프로필 사진, 이메일, 닉네임, 등급, 등급 점수)를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내 정보 조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = MemberInfoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<MemberInfoResponse> getMyProfile();

    @Operation(
            summary = "사용자 정보 조회 - JWT O",
            description = "사용자 정보(프로필 사진, 이메일, 닉네임, 등급, 등급 점수)를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 정보 조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = MemberInfoResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<MemberInfoResponse> getMemberProfile(
            @Parameter(description = "사용자 정보를 조회할 사용자 id", example = "1", required = true)
            Long memberId
    );

    @Operation(
            summary = "내가 좋아요 누른 레시피 목록 조회 - JWT O",
            description = "내가 좋아요 누른 레시피 목록들이 조회된다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내가 좋아요 누른 레시피 목록 조회 성공, 내가 좋아요 누른 레시피가 없을 경우 빈 리스트 반환",
                    content = @Content(
                            schema = @Schema(implementation = LikeRecipePreviewResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<LikeRecipePreviewResponse> getLikedRecipes();

    @Operation(
            summary = "내가 쓴 댓글 레시피 목록 조회 - JWT O",
            description = "내가 쓴 댓글의 레시피 목록들이 조회된다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내가 쓴 댓글 레시피 목록 조회 성공, 내가 쓴 댓글 레시피가 없을 경우 빈 리스트 반환",
                    content = @Content(
                            schema = @Schema(implementation = CommentRecipePreviewResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<CommentRecipePreviewResponse> getWriteCommentRecipes();

    @Operation(
            summary = "내가 찜한 레시피 목록 조회 - JWT O",
            description = "내가 찜한 레시피 목록들이 조회된다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내가 찜한 레시피 목록 조회 성공, 내가 찜한 레시피가 없을 경우 빈 리스트 반환",
                    content = @Content(
                            schema = @Schema(implementation = FavoritesRecipePreviewResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<FavoritesRecipePreviewResponse> getMyFavoritesRecipes();

    @Operation(
            summary = "로그아웃 - JWT O",
            description = "로그아웃 시 로그인 페이지로 이동."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "로그아웃 성공"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<Void> logout();

}