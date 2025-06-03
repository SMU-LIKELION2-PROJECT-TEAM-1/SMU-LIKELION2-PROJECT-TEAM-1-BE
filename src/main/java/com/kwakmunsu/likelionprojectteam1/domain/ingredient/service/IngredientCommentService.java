package com.kwakmunsu.likelionprojectteam1.domain.ingredient.service;

import com.kwakmunsu.likelionprojectteam1.domain.ingredient.entity.Ingredient;
import com.kwakmunsu.likelionprojectteam1.domain.ingredient.repository.IngredientRepository;
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

    @Scheduled(cron = "0 0 0 * * MON")
    public void createChallengeCandidate() {
        List<IngredientDescription> ingredientDescriptions = chatProvider.getIngredient();

        ingredientDescriptions.stream()
                .map(i -> Ingredient.builder()
                        .title(i.ingredient())
                        .description(i.description())
                        .build()
                )
                .forEach(ingredientRepository::save);
    }

}