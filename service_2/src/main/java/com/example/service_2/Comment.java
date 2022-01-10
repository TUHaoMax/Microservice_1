package com.example.service_2;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "blogid")
    private Blog blog;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private String comments;

    public Comment(Blog blog, Integer score, String comments) {
        this(null,blog,score,comments);
    }
}
