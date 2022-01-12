package com.example.gui_web.Service_Controller;

import com.example.gui_web.Author;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@Log
public class Author_Controller {
    @Autowired
    private RestTemplate restTemplate;

    public Author getAuthor(Long id) {
        log.info("getAuthor()"+id);

        ResponseEntity<Author> response = restTemplate.exchange(
                "http://localhost:5555/API/Author/resources/author/"+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public List<Author> getAuthors() {
        log.info("getAllAuthors()");

        ResponseEntity<List<Author>> response = restTemplate.exchange(
                "http://localhost:5555/API/Author/resources/author",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

   public Long checkAuthor(String name){
        List<Author> authorList=getAuthors();

        for (Author author:authorList){
            if (author.getName().equals(name) ){
                return author.getId();
            }
        }

        return Long.valueOf(0);
   }

    public HttpHeaders addAuthor(Author author) {
        log.info("addAuthor()"+author);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:5555/API/Author/resources/author",
                author,String.class
        );

        return response.getHeaders();
    }

    public void updateAuthor(Author author,long paid,int IorP) {
        log.info("updateAuthor()"+author);

        restTemplate.put(
                "http://localhost:5555/API/Author/resources/author/"+author.getId()+"/"+paid+"/"+IorP,
                author,
                String.class
        );

    }
}
