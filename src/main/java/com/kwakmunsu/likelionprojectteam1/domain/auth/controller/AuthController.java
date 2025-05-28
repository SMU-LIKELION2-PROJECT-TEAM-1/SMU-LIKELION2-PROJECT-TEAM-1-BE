package com.kwakmunsu.likelionprojectteam1.domain.auth.controller;

import com.kwakmunsu.likelionprojectteam1.domain.auth.controller.dto.ReissueTokenRequest;
import com.kwakmunsu.likelionprojectteam1.domain.auth.service.AuthCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.auth.service.AuthQueryService;
import com.kwakmunsu.likelionprojectteam1.global.oauth2.jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController extends AuthDocsController {

    private final AuthCommandService authCommandService;
    private final AuthQueryService authQueryService;

    @Override
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestBody ReissueTokenRequest request) {
        TokenResponse response = authCommandService.reissue(request.refreshToken());

        return ResponseEntity.ok(response);
    }

}