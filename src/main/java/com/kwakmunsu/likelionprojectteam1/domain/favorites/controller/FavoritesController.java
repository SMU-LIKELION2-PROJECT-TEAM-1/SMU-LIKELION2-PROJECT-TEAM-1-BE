package com.kwakmunsu.likelionprojectteam1.domain.favorites.controller;

import com.kwakmunsu.likelionprojectteam1.domain.favorites.service.FavoritesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/favorites")
@RequiredArgsConstructor
@RestController
public class FavoritesController extends FavoritesDocsController {

    private final FavoritesCommandService favoritesCommandService;

}