package com.example.gui_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class ControllerUI {
    @Autowired
    private ControllerBlog controllerBlog;

    @GetMapping
    String getBlog(Model model){
        model.addAttribute("msg",controllerBlog.getBlogs());
        return "blog";
    }

    @GetMapping("/new")
    String putBlog(Model model){
        return "ADD";
    }

    @GetMapping("/edit")
    String editBlog(Model model){
        model.addAttribute("msg",controllerBlog.getBlog(1));
        return "Edit";
    }

}
