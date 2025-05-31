package com.kwakmunsu.likelionprojectteam1.domain.view.repository;

import com.kwakmunsu.likelionprojectteam1.domain.view.entity.View;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ViewRepository {

    private final ViewJpaRepository viewJpaRepository;

    public void deleteByRecipeId(Long recipeId) {
        viewJpaRepository.deleteByRecipeId(recipeId);
    }

    public boolean existsMemberIdAndRecipeId(Long memberId, Long recipeId) {
        return viewJpaRepository.existsByMemberIdAndRecipeId(memberId, recipeId);
    }

    public void save(View view) {
        viewJpaRepository.save(view);
    }

}