package com.kwakmunsu.likelionprojectteam1.domain.like.controller;

import com.kwakmunsu.likelionprojectteam1.domain.like.service.LikeCommandService;
import com.kwakmunsu.likelionprojectteam1.global.annotation.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recipes")
@RequiredArgsConstructor
@RestController
public class LikeController extends LikeDocsController {

    private final LikeCommandService likeCommandService;

    @Override
    @PostMapping("{recipeId}/likes")
    public ResponseEntity<Void> like(
            @AuthMember Long memberId,
            @PathVariable(name = "recipeId") Long recipeId
    ) {
        likeCommandService.like(recipeId, memberId);

        return ResponseEntity.ok().build();
    }

}