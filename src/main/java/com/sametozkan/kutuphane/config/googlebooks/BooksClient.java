package com.sametozkan.kutuphane.config.googlebooks;

import com.sametozkan.kutuphane.config.chatgpt.ChatRequest;
import com.sametozkan.kutuphane.config.chatgpt.ChatResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface BooksClient {
    @GetExchange("/books/v1/volumes?q=isbn:{isbn}")
    String getByIsbn(@PathVariable Long isbn);
}
