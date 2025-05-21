package com.kwakmunsu.likelionprojectteam1.domain.member.controller;

import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberCommendService;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController extends MemberDocsController {

    private final MemberCommendService memberCommendService;
    private final MemberQueryService memberQueryService;

}