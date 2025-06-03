package com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.OpenAIConfig;
import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto.ChatMessage;
import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto.ChatResponse;
import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto.IngredientDescription;
import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto.request.ChatRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class ChatProvider {

    private final OpenAIConfig openAIConfig;
    private final RestTemplate restTemplate;

    public List<IngredientDescription> getIngredient() {
        ChatRequest request = createChatRequest();
        String content = postPrompt(request);

        return parseToIngredientDescription(content);
    }

    private ChatRequest createChatRequest() {
        String prompt = """
                요리 재료 2가지를 추천해줘. 각 재료에 대해 설명도 함께 알려줘.
                아래와 같은 JSON 형태로 답변해줘. (description 은 90~100글자로 설명해줘).

                [
                  {"ingredient": "재료명", "description": 여기에 90~100글자 설명},
                  {"ingredient": "재료명", "description": 여기에 90~100글자 설명}
                ]
                """;
        ChatMessage message = new ChatMessage("user", prompt);
        // 바꿀 예정 yml에 파일에서 가져오는걸로
        return new ChatRequest(openAIConfig.getModel(), List.of(message));
    }

    private String postPrompt(ChatRequest request) {
        String url = openAIConfig.getApiURL();
        HttpHeaders headers = openAIConfig.httpHeaders();
        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        ChatResponse response = restTemplate.postForObject(url, entity, ChatResponse.class);

        return response.choices().get(0).message().content();
    }

    private List<IngredientDescription> parseToIngredientDescription(String content) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(content, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}