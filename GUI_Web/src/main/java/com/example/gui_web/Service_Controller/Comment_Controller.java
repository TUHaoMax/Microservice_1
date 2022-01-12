package com.example.gui_web.Service_Controller;

import com.example.gui_web.Comments;
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
public class Comment_Controller {
    @Autowired
    private RestTemplate restTemplate;

    public String addComment(Comments comments) {
        log.info("addComment()"+comments);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:5555/API/Comment/resources/comments",
                comments,
                String.class
        );

        return response.getBody();
    }

    public List<Comments> getComments(Long id) {
        log.info("getComments_from_blogid"+id);

        ResponseEntity<List<Comments>> response = restTemplate.exchange(
                "http://localhost:5555/API/Comment/resources/comments/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }
}
