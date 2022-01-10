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
@RequestMapping("/resources/comments")
@Log
public class CommentResource {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;



    public Comment retrieve(long id) {
        log.info("retrieve() >> id=" + id);

        return commentRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    public Blog blogretrieve(long id) {
        log.info("retrieve() >> id=" + id);

        return blogRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    @GetMapping("/{id}")
    public List<Comment> retrieveAll(@PathVariable long id) {
        log.info("retrieveAll()"+id);

        return commentRepository.findCommentByBlog(blogretrieve(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Comment comment) {
        log.info("create() >> news=" + comment);

        comment.setId(null);   // better safe than sorry
        comment = commentRepository.save(comment);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).retrieve(comment.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

}
