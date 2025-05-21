package com.kwakmunsu.likelionprojectteam1.domain.like.controller;

import com.kwakmunsu.likelionprojectteam1.domain.like.service.LikeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/like")
@RequiredArgsConstructor
@RestController
public class LikeController extends LikeDocsController {

    private final LikeCommandService likeCommandService;

}