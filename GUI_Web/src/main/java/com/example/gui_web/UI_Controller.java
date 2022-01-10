package com.example.gui_web;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log
public class UI_Controller {
    @Autowired
    private Service_Controller serviceController;

    @GetMapping
    String getBlog(Model model){
        model.addAttribute("msg", serviceController.getBlogs());
        return "blog";
    }

    @GetMapping("/new")
    String newBlog(Model model){
        return "ADD";
    }

    @GetMapping("/Details/{id}")
    String editBlog(Model model,@PathVariable String id){
        log.info(String.valueOf(id));
        model.addAttribute("msg", serviceController.getBlog(Long.valueOf(id)));
        model.addAttribute("coms", serviceController.getComments(Long.valueOf(id)));
        return "Details";
    }

    @PostMapping("/add")
    String addBlog(Model model,Blog blog){
        log.info(serviceController.addBlog(blog));

        model.addAttribute("msg", serviceController.getBlogs());
        return "blog";
    }

    @GetMapping("/Update")
    String updateBlog(Model model,Blog blog){
        log.info("update{}"+blog);
        serviceController.updateBlog(blog);
        model.addAttribute("msg", serviceController.getBlogs());
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
        Blog blog= serviceController.getBlog(Long.valueOf(id));
        comments.setBlog(blog);
        blog.setPopular(blog.getPopular()+1);
        serviceController.updateBlog(blog);
        log.info(serviceController.addComment(comments));


        model.addAttribute("msg", serviceController.getBlogs());
        return "blog";
    }
}
