package com.kwakmunsu.likelionprojectteam1.domain.like.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LikeRepository {

    private final LikeJpaRepository likeJpaRepository;

}