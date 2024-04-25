package com.sametozkan.kutuphane.config.chatgpt;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface GptClient {
    @PostExchange("/v1/chat/completions")
    ChatResponse chat(@RequestBody ChatRequest request);
}
