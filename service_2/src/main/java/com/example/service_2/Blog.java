package com.example.service_2;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String attractions;

    @Column(nullable = false, length = 10000)
    private String description;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false)
    private Long popular;

    public Blog(String title, String description, String author, Long popular) {
        this(null,title,description,author,popular);
    }
}
