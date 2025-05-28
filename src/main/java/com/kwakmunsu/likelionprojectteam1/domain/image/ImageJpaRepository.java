package com.kwakmunsu.likelionprojectteam1.domain.image;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<Image, Long> {

    List<Image> findByRecipeId(Long recipeId);

    void deleteByName(String name);

    void deleteByRecipeId(Long recipeId);

}