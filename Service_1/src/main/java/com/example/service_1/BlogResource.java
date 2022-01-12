package com.example.service_1;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resources/blogs")
@Log
public class BlogResource {
    @Autowired
    private BlogRepository BlogRepository;

    private int counter;

    @GetMapping
    public List<Blog> getAllblog() {
        log.info("getAllblog()");

        return BlogRepository.findAll(Sort.by(Sort.Direction.DESC,"popular"));
    }


    @GetMapping("/{id}")
    public Blog getBlog(@PathVariable long id) {
        log.info("getBlog() >> id=" + id);

        return BlogRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Blog blog) {
        log.info("create() >> blog=" + blog);

        blog.setId(null);
        blog = BlogRepository.save(blog);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).getBlog(blog.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Blog blog) {
        //log.info("update() >> id=" + id + ", Blog=" + blog);

        blog.setId(id);
        log.info("update() >> id=" + id + ", Blog=" + blog);
        BlogRepository.save(blog);
    }



}
