package com.kwakmunsu.likelionprojectteam1.domain.view.service;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.view.entity.View;
import com.kwakmunsu.likelionprojectteam1.domain.view.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ViewCommandService {

    private final ViewRepository viewRepository;
    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;

    public void increment(Long memberId, Long recipeId) {
        if (viewRepository.existsMemberIdAndRecipeId(memberId, recipeId)) {
            return;
        }
        Recipe recipe = recipeRepository.findById(recipeId);
        Member member = memberRepository.findById(memberId);
        View view = View.builder()
                .member(member)
                .recipe(recipe)
                .build();

        viewRepository.save(view);
    }

}