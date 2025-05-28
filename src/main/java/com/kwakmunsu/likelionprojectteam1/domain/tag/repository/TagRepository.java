package com.kwakmunsu.likelionprojectteam1.domain.tag.repository;

import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Tag;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TagRepository {

    private final TagJpaRepository tagJpaRepository;

    public void save(Tag tag) {
        tagJpaRepository.save(tag);
    }

    public Tag findByRecipeId(Long recipeId) {
        return tagJpaRepository.findByRecipeId(recipeId)
                .orElseThrow(() -> new NotFoundException("해당 레시피의 Tag를 찾을 수 없습니다."));
    }

    public void deleteByRecipeId(Long recipeId) {
        tagJpaRepository.deleteByRecipeId(recipeId);
    }

}