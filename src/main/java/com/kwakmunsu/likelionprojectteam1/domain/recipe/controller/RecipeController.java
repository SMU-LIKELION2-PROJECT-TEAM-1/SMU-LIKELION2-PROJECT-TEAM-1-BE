package com.kwakmunsu.likelionprojectteam1.domain.recipe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recipes")
@RequiredArgsConstructor
@RestController
public class RecipeController extends RecipeDocsController {

}