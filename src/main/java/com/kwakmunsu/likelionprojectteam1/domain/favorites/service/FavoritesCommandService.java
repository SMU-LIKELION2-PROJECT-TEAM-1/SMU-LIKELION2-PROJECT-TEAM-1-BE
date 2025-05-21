package com.kwakmunsu.likelionprojectteam1.domain.favorites.service;

import com.kwakmunsu.likelionprojectteam1.domain.favorites.repository.FavoritesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FavoritesCommandService {

    private final FavoritesRepository favoritesRepository;

}