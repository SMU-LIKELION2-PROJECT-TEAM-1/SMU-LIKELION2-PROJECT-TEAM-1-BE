package com.kwakmunsu.likelionprojectteam1.domain.comment.controller;

import com.kwakmunsu.likelionprojectteam1.domain.comment.controller.dto.CommentCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.comment.service.CommentCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recipes")
@RequiredArgsConstructor
@RestController
public class CommentController extends CommentDocsController {

    private final CommentCommandService commentCommandService;

    @Override
    @PostMapping("{recipeId}/comments")
    public ResponseEntity<Long> create(
            @PathVariable(name = "recipeId") Long recipeId,
            @RequestBody CommentCreateRequest request
    ) {
        return ResponseEntity.ok(1L);
    }

    @Override
    @DeleteMapping("{commentId}/comments")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("{commentId}/comments")
    public ResponseEntity<Void> update(@PathVariable Long commentId) {
        return ResponseEntity.noContent().build();
    }

}