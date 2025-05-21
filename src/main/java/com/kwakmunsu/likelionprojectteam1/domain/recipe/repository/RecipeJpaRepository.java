package com.kwakmunsu.likelionprojectteam1.domain.recipe.repository;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeJpaRepository extends JpaRepository<Recipe, Long> {

}