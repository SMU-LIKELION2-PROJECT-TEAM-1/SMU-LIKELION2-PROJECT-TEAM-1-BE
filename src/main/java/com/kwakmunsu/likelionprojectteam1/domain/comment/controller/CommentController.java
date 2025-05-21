package com.kwakmunsu.likelionprojectteam1.domain.comment.controller;

import com.kwakmunsu.likelionprojectteam1.domain.comment.service.CommentCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comments")
@RequiredArgsConstructor
@RestController
public class CommentController extends CommentDocsController {

    private final CommentCommandService commentCommandService;

}