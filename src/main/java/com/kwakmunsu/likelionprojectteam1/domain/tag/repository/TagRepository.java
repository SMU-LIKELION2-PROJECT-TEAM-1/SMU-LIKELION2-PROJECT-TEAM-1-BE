package com.kwakmunsu.likelionprojectteam1.domain.tag.repository;

import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TagRepository {

    private final TagJpaRepository tagJpaRepository;

    public void save(Tag tag) {
        tagJpaRepository.save(tag);
    }

}