package com.kwakmunsu.likelionprojectteam1.domain.auth.service;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberQueryService;
import com.kwakmunsu.likelionprojectteam1.global.exception.UnAuthenticationException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.dto.TokenResponse;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthCommandService {

    private final JwtProvider jwtProvider;
    private final MemberQueryService memberQueryService;

    @Transactional
    public TokenResponse reissue(String refreshToken) {
        if (jwtProvider.isNotValidateToken(refreshToken)) {
            throw new UnAuthenticationException(ErrorMessage.INVALID_TOKEN.getMessage());
        }
        Member member = memberQueryService.findByRefreshToken(refreshToken);

        return getTokenResponse(member);
    }

    private TokenResponse getTokenResponse(Member member) {
        TokenResponse tokenResponse = jwtProvider.createTokens(member.getId(), member.getRole());
        member.updateRefreshToken(tokenResponse.refreshToken());
        return tokenResponse;
    }
}