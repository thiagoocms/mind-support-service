package com.nassau.mind_support_service.dto.chatgpt;

import java.util.List;

public record ChatBody(
        String model,
        List<ChatMessage> messages
) {
}
