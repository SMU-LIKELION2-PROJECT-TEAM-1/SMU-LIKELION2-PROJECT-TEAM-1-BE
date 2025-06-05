package com.kwakmunsu.likelionprojectteam1.domain.ingredient.controller;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.controller.dto.request.VoteCreateRequest;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.IngredientCommentService;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.IngredientQueryService;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.response.IngredientCandidateResponse;
import com.kwakmunsu.likelionprojectteam1.global.annotation.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ingredients")
@RequiredArgsConstructor
@RestController
public class IngredientController extends IngredientDocsController {

    private final IngredientQueryService ingredientQueryService;
    private final IngredientCommentService ingredientCommentService;

    @Override
    @GetMapping
    public ResponseEntity<IngredientCandidateResponse> getChallengeCandidate() {
        IngredientCandidateResponse response = ingredientQueryService.getChallengeCandidate();

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/{ingredientId}/votes")
    public ResponseEntity<Long> vote(@AuthMember Long memberId, @RequestBody VoteCreateRequest request) {
        Long voteCount = ingredientCommentService.vote(request.toServiceRequest(memberId));

        return ResponseEntity.ok(voteCount);
    }

}