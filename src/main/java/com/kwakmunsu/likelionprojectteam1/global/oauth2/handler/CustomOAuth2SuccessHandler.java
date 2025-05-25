package com.kwakmunsu.likelionprojectteam1.global.oauth2.handler;

import static com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.common.TokenType.ACCESS;
import static com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.common.TokenType.REFRESH;

import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberCommendService;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.dto.CustomOAuth2Member;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.dto.TokenResponse;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.provider.JwtProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final MemberCommendService memberCommendService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        CustomOAuth2Member oAuth2Member = (CustomOAuth2Member) authentication.getPrincipal();
        TokenResponse tokenResponse = jwtProvider.createTokens(oAuth2Member.getMemberId(), oAuth2Member.getRole());

        memberCommendService.updateRefreshToken(oAuth2Member.getMemberId(), tokenResponse.refreshToken());
        log.info("소셜 로그인 성공: " + oAuth2Member.getName());
        String redirectUrl = createRedirectUrl(tokenResponse);
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private String createRedirectUrl(TokenResponse tokenResponse) {
        // TODO: 배포 시 url 수정 예정
        return UriComponentsBuilder.fromUriString("http://localhost:3000/")
                .queryParam(ACCESS.getValue(), tokenResponse.accessToken())
                .queryParam(REFRESH.getValue(), tokenResponse.refreshToken())
                .build()
                .toUriString();
    }

}