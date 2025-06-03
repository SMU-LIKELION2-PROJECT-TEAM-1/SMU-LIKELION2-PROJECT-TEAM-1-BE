package com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto.request;

import com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto.ChatMessage;
import java.util.List;

public record ChatRequest(String model, List<ChatMessage> messages) {

}