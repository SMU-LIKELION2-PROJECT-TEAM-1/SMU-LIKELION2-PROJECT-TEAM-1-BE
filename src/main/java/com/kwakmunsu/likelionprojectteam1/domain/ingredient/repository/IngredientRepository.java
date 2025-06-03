package com.kwakmunsu.likelionprojectteam1.domain.ingredient.repository;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.entity.Ingredient;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.IngredientDetailResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class IngredientRepository {

    private final IngredientJpaRepository ingredientJpaRepository;

    public void save(Ingredient ingredient) {
        ingredientJpaRepository.save(ingredient);
    }

    public List<IngredientDetailResponse> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        List<Ingredient> ingredients = ingredientJpaRepository.findAllByCreatedAtBetween(start, end);

        return ingredients.stream()
                .map(i -> new IngredientDetailResponse(i.getTitle(), i.getDescription()))
                .toList();
    }

}