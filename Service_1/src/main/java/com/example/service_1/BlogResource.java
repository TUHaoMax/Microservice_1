package com.example.service_1;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public List<Blog> retrieveAll() {
        log.info("retrieveAll()");

        // simulates problem (exception) on every 3rd request
        /*counter = (counter + 1) % 3;
        if (counter == 0) {
            throw new RuntimeException("dummy");
        }*/

        return BlogRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("delete() >> id=" + id);

        BlogRepository.deleteById(id);   // throw EmptyResultDataAccessException if news could not be found
    }

    @GetMapping("/{id}")
    public Blog retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);

        return BlogRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Blog blog) {
        log.info("create() >> news=" + blog);

        blog.setId(null);   // better safe than sorry
        blog = BlogRepository.save(blog);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(blog.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Blog blog) {
        log.info("update() >> id=" + id + ", news=" + blog);

        blog.setId(id);   // better safe than sorry
        BlogRepository.save(blog);
    }



}
