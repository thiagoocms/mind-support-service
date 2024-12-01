package com.nassau.mind_support_service.service;

import com.nassau.mind_support_service.dto.chatgpt.ChatBody;
import com.nassau.mind_support_service.dto.chatgpt.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGPTService {

    private final RestTemplate restTemplate;

    @Value("${chat-gpt.api-key}")
    private String token;

    @Value("${chat-gpt.model-id}")
    private String modelId;

    @Autowired
    public ChatGPTService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object model() throws URISyntaxException {
        var url = "https://api.openai.com/v1/models";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(new URI(url), HttpMethod.GET, entity, Object.class);
    }

    public Object chat() throws URISyntaxException {
        var url = "https://api.openai.com/v1/chat/completions";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        List<ChatMessage> messageList = new ArrayList<>();
        messageList.add(new ChatMessage("system", "Como você se sente quando isso acontece?"));
        messageList.add(new ChatMessage("user", "Desconforto físico (ex: coração acelerado)"));
        ChatBody body = new ChatBody(modelId, messageList);
        HttpEntity<ChatBody> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(new URI(url), HttpMethod.POST, entity, Object.class);
    }
}
