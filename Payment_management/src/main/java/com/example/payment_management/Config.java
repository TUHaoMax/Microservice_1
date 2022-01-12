package com.example.payment_management;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class Config {
    @Bean
    public Consumer<AuthorEvent> handleReaderEvent(Export export) {
        return event -> {
            Author author = event.getAuthor();
            export.record(event,author.getName());

        };
    }
}
