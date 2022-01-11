package com.example.gui_web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@Log
public class Service_Controller {
    @Autowired
    private RestTemplate restTemplate;

    public List<Blog> getBlogs() {
        log.info("getBlogs()");

        ResponseEntity<List<Blog>> response = restTemplate.exchange(
                "http://localhost:5555/API/blog/resources/blogs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public List<Comments> getComments(Long id) {
        log.info("getComments()");

        ResponseEntity<List<Comments>> response = restTemplate.exchange(
                "http://localhost:5555/API/Comment/resources/comments/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }


    public Blog getBlog(Long id) {
        log.info("getBlog()");

        ResponseEntity<Blog> response = restTemplate.exchange(
                "http://localhost:5555/API/blog/resources/blogs/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public String addBlog(Blog blog) {
        log.info("addBlog()");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:5555/API/blog/resources/blogs",
                blog,String.class
        );

        return response.getBody();
    }

    public String addComment(Comments comments) {
        log.info("addComment()");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:5555/API/Comment/resources/comments",
                comments,
                String.class
        );

        return response.getBody();
    }

    public void updateBlog(Blog blog) {
        log.info("updateBlog()");

        restTemplate.put(
                "http://localhost:5555/API/blog/resources/blogs/"+blog.getId(),
                blog,
                String.class
        );

    }



}
