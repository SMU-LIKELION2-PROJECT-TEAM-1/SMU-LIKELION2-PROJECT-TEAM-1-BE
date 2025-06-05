package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service;

import com.kwakmunsu.likelionprojectteam1.domain.challenge.entity.Challenge;
import com.kwakmunsu.likelionprojectteam1.domain.challenge.repository.ChallengeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.entity.Ingredient;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.repository.IngredientRepository;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.request.VoteCreateServiceRequest;
import com.kwakmunsu.likelionprojectteam1.domain.vote.entity.Vote;
import com.kwakmunsu.likelionprojectteam1.domain.vote.repository.VoteRepository;
import com.kwakmunsu.likelionprojectteam1.global.exception.DuplicationException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.ChatProvider;
import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto.IngredientDescription;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredientCommentService {

    private final ChatProvider chatProvider;
    private final IngredientRepository ingredientRepository;
    private final VoteRepository voteRepository;
    private final ChallengeRepository challengeRepository;

    @Scheduled(cron = "0 0 0 * * MON")
    public void createChallengeCandidate() {
        // 삭제 로직 추가 또는 업데이트
        List<IngredientDescription> ingredientDescriptions = chatProvider.getIngredient();
        Challenge challenge = Challenge.builder().build();
        Long challengeId = challengeRepository.save(challenge);

        ingredientDescriptions.stream()
                .map(i -> Ingredient.builder()
                        .challengeId(challengeId)
                        .title(i.ingredient())
                        .description(i.description())
                        .build()
                )
                .forEach(ingredientRepository::save);
    }

    public Long vote(VoteCreateServiceRequest request) {
        if (voteRepository.existsMemberIdAndChallengeId(request.memberId(), request.challengeId())) {
            throw new DuplicationException(ErrorMessage.DUPLICATE_VOTE.getMessage());
        }
        Vote vote = Vote.builder()
                .challengeId(request.challengeId())
                .memberId(request.memberId())
                .ingredientId(request.ingredientId())
                .build();

        Long ingredientId = voteRepository.save(vote);
        return voteRepository.countByIngredientId(ingredientId);
    }

}