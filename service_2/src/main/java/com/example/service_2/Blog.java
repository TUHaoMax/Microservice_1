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

    @Column(nullable = false, length = 1000)
    private String attractions;

    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "authorid")
    private Author author;

    @Column(nullable = false)
    private Long popular;

    public Blog(String attractions, String description, Author author, Long popular) {
        this(null,attractions,description,author,popular);
    }
}
