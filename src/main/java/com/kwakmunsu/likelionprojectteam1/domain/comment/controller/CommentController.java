package com.kwakmunsu.likelionprojectteam1.domain.comment.controller;

import com.kwakmunsu.likelionprojectteam1.domain.comment.controller.dto.CommentCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.comment.controller.dto.CommentUpdateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.comment.service.CommentCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.comment.service.dto.response.CommentCreateResponse;
import com.kwakmunsu.likelionprojectteam1.global.annotation.AuthMember;
import jakarta.validation.Valid;
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
    public ResponseEntity<CommentCreateResponse> create(
            @AuthMember Long memberId,
            @PathVariable(name = "recipeId") Long recipeId,
            @Valid @RequestBody CommentCreateRequest request
    ) {
        CommentCreateResponse response = commentCommandService.create(request.toServiceRequest(memberId), recipeId);
        return ResponseEntity.ok(response);
    }

    @Override
    @PatchMapping("{commentId}/comments")
    public ResponseEntity<Void> update(
            @AuthMember Long memberId,
            @PathVariable(name = "commentId") Long commentId,
            @Valid @RequestBody CommentUpdateRequest request
    ) {
        commentCommandService.update(request.toServiceRequest(memberId), commentId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("{commentId}/comments")
    public ResponseEntity<Void> delete(@PathVariable Long commentId) {
        return ResponseEntity.noContent().build();
    }

}