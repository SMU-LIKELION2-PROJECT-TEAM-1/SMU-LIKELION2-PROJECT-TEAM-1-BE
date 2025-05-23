package com.kwakmunsu.likelionprojectteam1.domain.member.service.dto;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record MemberInfoResponse(

        @Schema(description = "회원 프로필 url", example = "wqe1kjok2418sdmma")
        String profileUrl,

        @Schema(description = "회원 닉네임", example = "아기사자 곽")
        String nickname,

        @Schema(description = "회원 이메일", example = "iii1234@gmail.com")
        String email,

        @Schema(description = "회원 등급", example = "GOLD")
        String grade,

        @Schema(description = "회원 등급 포인트", example = "888")
        int point
) {

}