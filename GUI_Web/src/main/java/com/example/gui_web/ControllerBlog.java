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
public class ControllerBlog {
    @Autowired
    private RestTemplate restTemplate;

    public List<Blog> getBlogs() {
        log.info("getBlogs()");

        ResponseEntity<List<Blog>> response = restTemplate.exchange(
                "http://localhost:5555/api_1/blog/resources/blogs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public Blog getBlog(int id) {
        log.info("getBlog()");

        ResponseEntity<Blog> response = restTemplate.exchange(
                "http://localhost:5555/api_1/blog/resources/blogs/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public Blog addBlog() {
        log.info("getBlog()");

        ResponseEntity<Blog> response = restTemplate.exchange(
                "http://localhost:5555/api_2/blog/resources/blogs",
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }


}
