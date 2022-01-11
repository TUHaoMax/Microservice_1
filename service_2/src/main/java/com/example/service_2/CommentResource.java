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



    public Comment getComment(long id) {
        log.info("getComment() >> id=" + id);

        return commentRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    public Blog getblog(long id) {
        log.info("getblog() >> id=" + id);

        return blogRepository.findById(id)
                .orElseThrow(
                        () -> new EmptyResultDataAccessException("can't find news with id " + id, 1)
                );
    }

    @GetMapping("/{id}")
    public List<Comment> getAllcomment(@PathVariable long id) {
        log.info("getAllcomment_from"+id);

        return commentRepository.findCommentByBlog(getblog(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Comment comment) {
        log.info("create() >> comment=" + comment);

        comment.setId(null);
        comment = commentRepository.save(comment);

        URI location = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(getClass()).getComment(comment.getId())
        ).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        log.info("delete() >> Blog_id=" + id);

        commentRepository.deleteAllByBlog(getblog(id));
        blogRepository.deleteById(id);
    }
}
