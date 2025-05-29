package com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Grade;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberInfoResponse(

        @Schema(description = "회원 id", example = "1")
        Long id,

        @Schema(description = "회원 프로필 url", example = "wqe1kjok2418sdmma")
        String profileUrl,

        @Schema(description = "회원 닉네임", example = "아기사자 곽")
        String nickname,

        @Schema(description = "회원 이메일", example = "iii1234@gmail.com")
        String email,

        @Schema(description = "회원 등급(SEEDLING_CHEF, FLAME_CHEF, LEGENDARY_CHEF)", example = "FLAME_CHEF")
        String grade,

        @Schema(description = "회원 등급 포인트", example = "888")
        int point
) {

    public static MemberInfoResponse from(
            Long id,
            String profileUrl,
            String nickname,
            String email,
            Grade grade,
            int point
    ) {
        return MemberInfoResponse.builder()
                .id(id)
                .profileUrl(profileUrl)
                .nickname(nickname)
                .email(email)
                .grade(grade.name())
                .point(point)
                .build();
    }

}