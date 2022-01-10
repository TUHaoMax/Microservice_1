package com.example.gui_web;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    private Long id;
    private Blog blog;
    private Integer score;
    private String comments;
}
