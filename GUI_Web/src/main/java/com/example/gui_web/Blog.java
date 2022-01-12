package com.example.gui_web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Long id;
    private String attractions;
    private String description;
    private Author author;
    private Long popular;

    public Blog(String attractions, String description, Author author, Long popular) {
        this(null,attractions,description,author,popular);
    }
}
