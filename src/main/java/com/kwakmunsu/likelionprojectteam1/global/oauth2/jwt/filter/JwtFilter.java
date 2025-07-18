package com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.filter;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.response.ErrorResponse;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.common.TokenType;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private static final List<String> EXCLUDE_PATHS = List.of(
            "/", "/oauth2/**", "/error", "/auth/reissue", "/swagger/**", "/swagger-ui/**", "/v3/api-docs/**",
            "/ingredients","/recipes**","/recipes/{recipeId}","/recipes/weekly"
    );
    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final ObjectMapper objectMapper;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        return EXCLUDE_PATHS.stream()
                .anyMatch(exclude -> pathMatcher.match(exclude, path));
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getTokenFromHeader(request);
        if (!StringUtils.hasText(token)) {
            sendErrorResponse(response, ErrorMessage.WRONG_AUTH_HEADER);
            return;
        }
        if (jwtProvider.isNotValidateToken(token)) {
            sendErrorResponse(response, ErrorMessage.INVALID_TOKEN);
            return;
        }

        Authentication authentication = jwtProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(TokenType.AUTHORIZATION_HEADER.getValue());
        if (bearerToken != null && bearerToken.startsWith(TokenType.BEARER_PREFIX.getValue())) {
            return bearerToken.substring(TokenType.BEARER_PREFIX.getValue().length());
        }

        return null;
    }

    private void sendErrorResponse(HttpServletResponse response, ErrorMessage errorMessage) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(UNAUTHORIZED.value())
                .status(UNAUTHORIZED.toString())
                .message(errorMessage.getMessage())
                .build();
        log.warn("JWT 예외: " + errorMessage.getMessage());

        String jsonToString = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonToString);
    }

}