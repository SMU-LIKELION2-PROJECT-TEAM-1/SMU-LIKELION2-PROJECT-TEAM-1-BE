package com.kwakmunsu.likelionprojectteam1.domain.tag.repository;

import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByRecipeId(Long recipeId);

    void deleteByRecipeId(Long recipeId);

}