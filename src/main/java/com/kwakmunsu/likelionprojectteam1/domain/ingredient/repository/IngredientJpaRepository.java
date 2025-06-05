package com.kwakmunsu.likelionprojectteam1.domain.ingredient.repository;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.entity.Ingredient;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientJpaRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}