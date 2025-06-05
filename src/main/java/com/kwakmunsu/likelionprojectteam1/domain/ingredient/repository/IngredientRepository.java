package com.kwakmunsu.likelionprojectteam1.domain.ingredient.repository;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.entity.Ingredient;
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

    public List<Ingredient> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return ingredientJpaRepository.findAllByCreatedAtBetween(start, end);
    }

}