package com.kwakmunsu.likelionprojectteam1.domain.view.controller;

import com.kwakmunsu.likelionprojectteam1.domain.view.service.ViewCommandService;
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
public class ViewController extends ViewDocsController{

    private final ViewCommandService viewCommandService;

    @PostMapping("/{recipeId}/views")
    public ResponseEntity<Void> view(@AuthMember Long memberId, @PathVariable Long recipeId) {
        viewCommandService.increment(memberId, recipeId);

        return ResponseEntity.ok().build();
    }
}