package com.kwakmunsu.likelionprojectteam1.domain.challenge.repository;

import com.kwakmunsu.likelionprojectteam1.domain.challenge.entity.Challenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChallengeRepository {
    private final ChallengeJpaRepository challengeJpaRepository;

    public Long save(Challenge challenge) {
        return challengeJpaRepository.save(challenge).getId();
    }

}