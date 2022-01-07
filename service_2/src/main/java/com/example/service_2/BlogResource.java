package com.example.service_2;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Blog retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);

        return BlogRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }


}
