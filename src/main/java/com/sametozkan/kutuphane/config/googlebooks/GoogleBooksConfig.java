package com.sametozkan.kutuphane.config.googlebooks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class GoogleBooksConfig {

    @Bean
    public BooksClient booksClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.newConnection()
                .responseTimeout(Duration.ofSeconds(60))
                .keepAlive(false);

        WebClient wc = builder.baseUrl("https://www.googleapis.com")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        WebClientAdapter wca = WebClientAdapter.create(wc);

        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(wca)
                .build()
                .createClient(BooksClient.class);
    }

}
