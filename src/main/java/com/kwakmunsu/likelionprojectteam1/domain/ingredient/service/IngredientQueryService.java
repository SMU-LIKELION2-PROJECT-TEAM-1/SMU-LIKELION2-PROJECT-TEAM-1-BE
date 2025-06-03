package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.repository.IngredientRepository;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.IngredientCandidateResponse;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.service.dto.IngredientDetailResponse;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngredientQueryService {

    private final IngredientRepository ingredientRepository;

    public IngredientCandidateResponse getChallengeCandidate() {
        LocalDateTime monStart = getPreviousWeekMonDay();
        LocalDateTime monEnd = getPreviousWeekMonDay()
                .withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        List<IngredientDetailResponse> responses = ingredientRepository.findAllByCreatedAtBetween(monStart, monEnd);

        return new IngredientCandidateResponse(responses);
    }

    // 처음 이라 저번주 데이터가 없어 이번주 데이터로 진행
    // 추후 바꿀 예정
    private LocalDateTime getPreviousWeekMonDay() {
        return LocalDateTime.now().plusDays(6)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .minusWeeks(1)
                .withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

}