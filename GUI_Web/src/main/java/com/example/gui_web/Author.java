package com.example.gui_web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private Long id;
    private String name;
    private Long reward;

    public Author(String name, Long reward) {
        this(null, name, reward);
    }
}
