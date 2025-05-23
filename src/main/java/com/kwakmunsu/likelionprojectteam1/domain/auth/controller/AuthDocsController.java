package com.kwakmunsu.likelionprojectteam1.domain.auth.controller;

import com.kwakmunsu.likelionprojectteam1.domain.auth.controller.dto.ReissueTokenRequest;
import com.kwakmunsu.likelionprojectteam1.domain.auth.service.dto.TokenResponse;
import com.kwakmunsu.likelionprojectteam1.global.SecurityOverrideCustomizer;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Auth Controller", description = "Auth API")
public abstract class AuthDocsController {

    @Operation(
            summary = "Access, Refresh Token 재발급 요청 - JWT O",
            description = "리프레시 토큰을 이용해 새로운 Access Token 및 Refresh Token을 재발급합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Access, Refresh Token 재발급 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TokenResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청(리프레시 토큰 누락 등)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패(리프레시 토큰 만료 / 위조)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    public abstract ResponseEntity<TokenResponse> reissue(ReissueTokenRequest request);

    @Tag(name = SecurityOverrideCustomizer.UNSECURED)
    @Operation(summary = "카카오 로그인", description = "카카오 소셜 로그인", security = @SecurityRequirement(name = ""))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TokenResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자입니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/oauth2/authorization/kakao")
    public void kakao() {
    }

    @Tag(name = SecurityOverrideCustomizer.UNSECURED)
    @Operation(summary = "구글 로그인", description = "구글 소셜 로그인", security = @SecurityRequirement(name = "public"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "로그인 성공",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TokenResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증되지 않은 사용자입니다.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/oauth2/authorization/google")
    public void google() {
    }
}