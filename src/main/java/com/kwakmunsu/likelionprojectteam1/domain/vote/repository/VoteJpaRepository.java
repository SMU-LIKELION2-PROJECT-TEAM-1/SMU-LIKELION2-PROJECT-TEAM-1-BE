package com.kwakmunsu.likelionprojectteam1.domain.vote.repository;

import com.kwakmunsu.likelionprojectteam1.domain.vote.entity.Vote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteJpaRepository extends JpaRepository<Vote, Long> {

    boolean existsByMemberIdAndChallengeId(Long memberId, Long ingredientId);

    List<Vote> findByChallengeId(Long challengeId);

    Long countByIngredientId(Long ingredientId);

}