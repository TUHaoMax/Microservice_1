package com.example.service_3;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/resources/author")
@Log
public class AuhtorResource {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable long id) {
        log.info("getAuthor() >> id=" + id);
        return authorRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    @GetMapping
    public List<Author> getAllauthor() {
        log.info("getAllauthor()");
        return authorRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Author author) {
        log.info("create() >> author=" + author);

        author.setId(null);
        author = authorRepository.save(author);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).getAuthor(author.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Author author) {
        log.info("update() >> id=" + id + ", author=" + author);

        author.setId(id);
        authorRepository.save(author);
    }
}
