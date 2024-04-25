package com.sametozkan.kutuphane.config.chatgpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class ChatGptConfig {

    @Value("${chatgpt.api.key}")
    private String apiKey;

    @Bean
    public GptClient gptClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.newConnection()
                .responseTimeout(Duration.ofSeconds(60))
                .keepAlive(false);

        WebClient wc = builder.baseUrl("https://api.openai.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        WebClientAdapter wca = WebClientAdapter.create(wc);

        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(wca)
                .build()
                .createClient(GptClient.class);
    }

}
