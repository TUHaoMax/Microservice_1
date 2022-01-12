package com.example.gui_web.Service_Controller;

import com.example.gui_web.Blog;
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
public class Blog_Controller {
    @Autowired
    private RestTemplate restTemplate;

    public List<Blog> getBlogs() {
        log.info("getAllBlogs()");

        ResponseEntity<List<Blog>> response = restTemplate.exchange(
                "http://localhost:5555/API/blog/resources/blogs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public Blog getBlog(Long id) {
        log.info("getBlog()"+id);

        ResponseEntity<Blog> response = restTemplate.exchange(
                "http://localhost:5555/API/blog/resources/blogs/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public String addBlog(Blog blog) {
        log.info("addBlog()"+blog);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:5555/API/blog/resources/blogs",
                blog,String.class
        );

        return response.getBody();
    }

    public void updateBlog(Blog blog) {
        log.info("updateBlog()"+blog);

        restTemplate.put(
                "http://localhost:5555/API/blog/resources/blogs/"+blog.getId(),
                blog,
                String.class
        );

    }
}
