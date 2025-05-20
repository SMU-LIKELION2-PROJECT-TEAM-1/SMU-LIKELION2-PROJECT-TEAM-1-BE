package com.kwakmunsu.likelionprojectteam1.domain.auth.controller;

import com.kwakmunsu.likelionprojectteam1.domain.auth.service.AuthCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.auth.service.AuthQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController extends AuthDocsController {

    private final AuthCommandService authCommandService;
    private final AuthQueryService authQueryService;

}