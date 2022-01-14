package com.example.gui_web;

import com.example.gui_web.Service_Controller.Author_Controller;
import com.example.gui_web.Service_Controller.Blog_Controller;
import com.example.gui_web.Service_Controller.Comment_Controller;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Setter
@Controller
@Log
public class UI_Controller {

    @Autowired
    private Blog_Controller blogController;

    @Autowired
    private Comment_Controller commentController;

    @Autowired
    private Author_Controller authorController;

    @Autowired
    private EventService eventService;


    @GetMapping
    String getBlog(Model model){

        model.addAttribute("msg", blogController.getBlogs());

        return "blog";
    }

    @GetMapping("/auhtor")
    String getAuthor(Model model){

        model.addAttribute("msg", authorController.getAuthors());

        return "Author";
    }

    @GetMapping("/message")
    String getMessage(Model model){
        log.info("GUI Event->"+eventService.EventMsg);
        model.addAttribute("msg",eventService.EventMsg);
        return "message";
    }

    @GetMapping("/SendReward/{id}")
    String SendReward(Model model,@PathVariable String id){

        Author author=authorController.getAuthor(Long.valueOf(id));
        long paid=author.getReward();
        author.setReward(0L);
        authorController.updateAuthor(author,paid,0);
        model.addAttribute("msg", authorController.getAuthors());
        return "Author";
    }


    @GetMapping("/new")
    String newBlog(Model model){
        return "ADD";
    }

    @GetMapping("/Details/{id}")
    String editBlog(Model model,@PathVariable String id){
        log.info(String.valueOf(id));

        Blog blog= blogController.getBlog(Long.valueOf(id));
        blog.setPopular(blog.getPopular()+1);
        log.info(String.valueOf(blog));
        blogController.updateBlog(blog);

        Author author=blog.getAuthor();
        author.setReward(author.getReward()+1);
        authorController.updateAuthor(author,1,1);

        model.addAttribute("msg", blogController.getBlog(Long.valueOf(id)));
        model.addAttribute("coms", commentController.getComments(Long.valueOf(id)));
        return "Details";
    }

    @PostMapping("/add")
    String addBlog(Model model,Blog blog,
                   @RequestParam String name
                   ){
        Author author;
       if (authorController.checkAuthor(name)==0){
           Author authorC=new Author(name, 0L);
           Long id=Long.valueOf(authorController.addAuthor(authorC).getLocation().getPath().split("/")[3]);
           author=authorController.getAuthor(id);
       }else {
           author=authorController.getAuthor(authorController.checkAuthor(name));
       }

        blog.setAuthor(author);
       log.info(blogController.addBlog(blog));

        model.addAttribute("msg", blogController.getBlogs());
        return "blog";
    }

    @GetMapping("/Update")
    String updateBlog(Model model,Blog blog,@RequestParam Long authorid){
        blog.setAuthor(authorController.getAuthor(authorid));
        log.info("update{}"+blog);
        blogController.updateBlog(blog);
        model.addAttribute("msg", blogController.getBlogs());
        return "blog";
    }

    @GetMapping("/Com/{id}")
    String newComment(Model model,@PathVariable String id){
       model.addAttribute("blog",id);

        return "Comment";
    }

    @PostMapping("/addCom/{id}")
    String addComment(Model model,Comments comments,@PathVariable String id){
        comments.setId(null);
        Blog blog= blogController.getBlog(Long.valueOf(id));
        comments.setBlog(blog);
        blog.setPopular(blog.getPopular()+1);

        blogController.updateBlog(blog);

        Author author=blog.getAuthor();
        author.setReward(author.getReward()+1);
        authorController.updateAuthor(author,1,1);

        log.info(commentController.addComment(comments));


        model.addAttribute("msg", blogController.getBlogs());
        return "blog";
    }
}
