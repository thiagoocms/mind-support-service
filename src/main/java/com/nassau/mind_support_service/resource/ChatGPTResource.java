package com.nassau.mind_support_service.resource;

import com.nassau.mind_support_service.constants.AppConstants;
import com.nassau.mind_support_service.service.ChatGPTService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = AppConstants.PATH + AppConstants.API + AppConstants.V1 + "chat-gpt")
public class ChatGPTResource {

    private final ChatGPTService chatGPTService;

    public ChatGPTResource(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @GetMapping("/model")
    public Object model() throws URISyntaxException {
        return chatGPTService.model();
    }

    @GetMapping("/chat")
    public Object chat() throws URISyntaxException {
        return chatGPTService.chat();
    }
}
