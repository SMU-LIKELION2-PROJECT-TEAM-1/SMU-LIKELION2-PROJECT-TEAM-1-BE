package com.kwakmunsu.likelionprojectteam1.domain.ingredient.controller;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.IngredientQueryService;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.IngredientCandidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/ingredients")
@RequiredArgsConstructor
@RestController
public class IngredientController {

    private final IngredientQueryService ingredientQueryService;

    @GetMapping
    public ResponseEntity<IngredientCandidateResponse> getIngredient() {
        IngredientCandidateResponse response = ingredientQueryService.getChallengeCandidate();

        return ResponseEntity.ok(response);
    }

}