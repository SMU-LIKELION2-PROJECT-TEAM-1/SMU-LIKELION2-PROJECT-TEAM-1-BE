package com.kwakmunsu.likelionprojectteam1.infrastructure.chatgpt.service.dto;

import java.util.List;

public record ChatResponse(List<ChatChoice> choices) {

}