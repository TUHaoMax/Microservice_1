package com.example.gui_web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.ws.rs.PUT;
import java.util.Arrays;

@Controller
@Log
public class ControllerUI {
    @Autowired
    private ControllerBlog controllerBlog;

    @GetMapping
    String getBlog(Model model){
        model.addAttribute("msg",controllerBlog.getBlogs());
        return "blog";
    }

    @GetMapping("/new")
    String newBlog(Model model){
        return "ADD";
    }

    @GetMapping("/Details/{id}")
    String editBlog(Model model,@PathVariable String id){
        log.info(String.valueOf(id));
        model.addAttribute("msg",controllerBlog.getBlog(Long.valueOf(id)));
        model.addAttribute("coms",controllerBlog.getComments(Long.valueOf(id)));
        return "Details";
    }

    @PostMapping("/add")
    String addBlog(Model model,Blog blog){
        log.info(controllerBlog.addBlog(blog));

        model.addAttribute("msg",controllerBlog.getBlogs());
        return "blog";
    }

    @GetMapping("/Update")
    String updateBlog(Model model,Blog blog){
        log.info("update{}"+blog);
        controllerBlog.updateBlog(blog);
        model.addAttribute("msg",controllerBlog.getBlogs());
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
        Blog blog=controllerBlog.getBlog(Long.valueOf(id));
        comments.setBlog(blog);
        blog.setPopular(blog.getPopular()+1);
        controllerBlog.updateBlog(blog);
        log.info(controllerBlog.addComment(comments));


        model.addAttribute("msg",controllerBlog.getBlogs());
        return "blog";
    }
}
