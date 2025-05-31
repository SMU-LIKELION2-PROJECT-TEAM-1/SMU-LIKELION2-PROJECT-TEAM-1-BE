package com.kwakmunsu.likelionprojectteam1.domain.member.controller;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.MyPageOption;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.MemberInfoResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipeInfinityPreviewResponse;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "Member Controller", description = "Member API")
public abstract class MemberDocsController {

    @Operation(
            summary = "내 정보 조회 - JWT O",
            description = """
                    내 정보(프로필 사진, 이메일, 닉네임, 등급, 등급 점수)를 조회합니다.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내 정보 조회 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MemberInfoResponse.class)
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
    public abstract ResponseEntity<MemberInfoResponse> getMyProfile(Long memberId);

    @Operation(
            summary = "사용자 정보 조회 - JWT O",
            description = "사용자 정보(프로필 사진, 이메일, 닉네임, 등급, 등급 점수)를 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 정보 조회 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MemberInfoResponse.class)
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
    public abstract ResponseEntity<MemberInfoResponse> getMemberProfile(
            @Parameter(description = "사용자 정보를 조회할 사용자 id", example = "1", required = true)
            Long memberId
    );

    @Operation(
            summary = "마이페이지에서 좋아요, 찜, 댓글 레시피 목록 조회 - JWT O",
            description = """
                    요청한 옵션의 레시피 목록들이 조회된다. LIKE, FAVORITES, COMMENT<br>
                    - 커서 기반으로 동작합니다.
                    - 예시
                        - 맨 처음 조회할 경우: http://localhost:8080?option=LIKE
                        - 스크롤 경우: http://localhost:8080?lastRecipeId=13&option=COMMENT
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "내가 선택한 옵션 레시피 목록 조회 성공, 내가 선택한 옵션의 레시피가 없을 경우 빈 리스트 반환",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RecipeInfinityPreviewResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(JWT 필요)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<RecipeInfinityPreviewResponse> getMyRecipes(
            Long memberId,
            Long lastRecipeId,
            @Schema(description = "마이페이지 에서 목록 확인 옵션", example = "LIKE || FAVORITES || COMMENT")
            MyPageOption option
    );

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
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public abstract ResponseEntity<Void> logout();

}