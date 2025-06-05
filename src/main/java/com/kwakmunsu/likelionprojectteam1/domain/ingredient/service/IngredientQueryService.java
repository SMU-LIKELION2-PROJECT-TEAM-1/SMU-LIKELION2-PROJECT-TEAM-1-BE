package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.entity.Ingredient;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.repository.IngredientRepository;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.response.IngredientCandidateResponse;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.response.IngredientDetailResponse;
import com.kwakmunsu.likelionprojectteam1.domain.vote.repository.VoteRepository;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class IngredientQueryService {

    private final IngredientRepository ingredientRepository;
    private final VoteRepository voteRepository;

    public IngredientCandidateResponse getChallengeCandidate() {
        LocalDateTime monStart = getPreviousWeekMonDay();
        LocalDateTime monEnd = getPreviousWeekMonDay().plusDays(6)
                .withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        log.info(monStart.toString());
        log.info(monEnd.toString());
        List<Ingredient> ingredients = ingredientRepository.findAllByCreatedAtBetween(monStart, monEnd);

        List<IngredientDetailResponse> responses = ingredients.stream()
                .map(i -> IngredientDetailResponse.builder()
                        .id(i.getId())
                        .challengeId(i.getChallengeId())
                        .title(i.getTitle())
                        .description(i.getDescription())
                        .count(voteRepository.countByIngredientId(i.getId()))
                        .build()
                )
                .toList();

        return new IngredientCandidateResponse(responses);
    }

    // 처음 이라 저번주 데이터가 없어 이번주 데이터로 진행
    // 추후 바꿀 예정
    private LocalDateTime getPreviousWeekMonDay() {
        return LocalDateTime.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

}