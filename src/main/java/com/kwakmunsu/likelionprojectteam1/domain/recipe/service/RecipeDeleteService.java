package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import com.kwakmunsu.likelionprojectteam1.domain.comment.repository.CommentRepository;
import com.kwakmunsu.likelionprojectteam1.domain.favorites.repository.FavoritesRepository;
import com.kwakmunsu.likelionprojectteam1.domain.image.ImageCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.like.repository.LikeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.tag.repository.TagRepository;
import com.kwakmunsu.likelionprojectteam1.domain.view.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeDeleteService {

    private final ImageCommandService imageCommandService;
    private final RecipeRepository recipeRepository;
    private final TagRepository tagRepository;
    private final ViewRepository viewRepository;
    private final CommentRepository commentRepository;
    private final FavoritesRepository favoritesRepository;
    private final LikeRepository likeRepository;

    // TODO: 최적화 할 수 있을 거 같음.
    @Transactional
    public void deleteByRecipeId(Long recipeId) {
        likeRepository.deleteByRecipeId(recipeId);
        viewRepository.deleteByRecipeId(recipeId);
        favoritesRepository.deleteByRecipeId(recipeId);
        commentRepository.deleteByRecipeId(recipeId);
        tagRepository.deleteByRecipeId(recipeId);
        imageCommandService.deleteByRecipeId(recipeId);
        recipeRepository.deleteById(recipeId);

    }

}