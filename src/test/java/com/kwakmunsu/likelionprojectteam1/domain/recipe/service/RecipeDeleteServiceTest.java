package com.kwakmunsu.likelionprojectteam1.domain.recipe.service;

import static org.mockito.Mockito.verify;

import com.kwakmunsu.likelionprojectteam1.domain.comment.repository.CommentRepository;
import com.kwakmunsu.likelionprojectteam1.domain.favorites.repository.FavoritesRepository;
import com.kwakmunsu.likelionprojectteam1.domain.image.ImageCommandService;
import com.kwakmunsu.likelionprojectteam1.domain.like.repository.LikeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.view.repository.ViewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecipeDeleteServiceTest {

    @Mock
    private ImageCommandService imageCommandService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ViewRepository viewRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private FavoritesRepository favoritesRepository;

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private RecipeDeleteService recipeDeleteService;

    @DisplayName("레시피를 삭제한다.")
    @Test
    void deleteRecipe() {
        // given
        Long recipeId = 1L;
        // when
        recipeDeleteService.deleteByRecipeId(recipeId);

        // then
        verify(likeRepository).deleteByRecipeId(recipeId);
        verify(viewRepository).deleteByRecipeId(recipeId);
        verify(favoritesRepository).deleteByRecipeId(recipeId);
        verify(commentRepository).deleteByRecipeId(recipeId);
        verify(imageCommandService).deleteByRecipeId(recipeId);
        verify(recipeRepository).deleteById(recipeId);
    }

}