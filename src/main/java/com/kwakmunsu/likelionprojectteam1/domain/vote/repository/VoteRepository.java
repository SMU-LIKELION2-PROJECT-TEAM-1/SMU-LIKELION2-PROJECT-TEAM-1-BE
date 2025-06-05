package com.kwakmunsu.likelionprojectteam1.domain.vote.repository;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.response.VoteDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.vote.entity.Vote;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VoteRepository {

    private final VoteJpaRepository voteJpaRepository;

    public boolean existsMemberIdAndChallengeId(Long memberId, Long challengeId) {
        return voteJpaRepository.existsByMemberIdAndChallengeId(memberId, challengeId);
    }

    public Long save(Vote vote) {
        return voteJpaRepository.save(vote).getIngredientId();
    }

    public Long countByIngredientId(Long ingredientId) {
        return voteJpaRepository.countByIngredientId(ingredientId);
    }

    public void findByChallengeId(Long challengeId) {
        List<Vote> votes = voteJpaRepository.findByChallengeId(challengeId);
        for (Vote vote : votes) {
            Long countByIngredientId = voteJpaRepository.countByIngredientId(vote.getIngredientId());
            VoteDetailResponse response = VoteDetailResponse.builder()
                    .ingredientId(vote.getIngredientId())
                    .count(countByIngredientId)
                    .build();
        }
    }

}