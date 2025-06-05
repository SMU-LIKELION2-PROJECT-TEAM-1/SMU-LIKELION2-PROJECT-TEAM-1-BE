package com.kwakmunsu.likelionprojectteam1.domain.challenge.repository;

import com.kwakmunsu.likelionprojectteam1.domain.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeJpaRepository extends JpaRepository<Challenge, Long> {
}