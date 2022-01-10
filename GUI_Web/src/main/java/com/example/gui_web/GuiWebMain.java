package com.example.gui_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GuiWebMain {
    @Autowired
    private Service_Controller serviceController;

    public static void main(String[] args) {

        SpringApplication.run(GuiWebMain.class, args);


    }
   /* @Scheduled(fixedDelay = 3000)
    public void run() {
        System.out.println("runging");

        try {
            controllerBlog.getBlogs()
                    .forEach(System.out::println);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }*/

}
