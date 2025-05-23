package com.kwakmunsu.likelionprojectteam1.domain.favorites.controller;

import com.kwakmunsu.likelionprojectteam1.domain.favorites.service.FavoritesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/favorites")
@RequiredArgsConstructor
@RestController
public class FavoritesController extends FavoritesDocsController {

    private final FavoritesCommandService favoritesCommandService;

    @Override
    @PostMapping("/{recipeId}")
    public ResponseEntity<Void> favorites(@PathVariable(name = "recipeId") Long recipeId) {
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> unFavorites(@PathVariable(name = "recipeId") Long recipeId) {
        return ResponseEntity.noContent().build();
    }

}