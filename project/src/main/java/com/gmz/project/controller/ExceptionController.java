package com.gmz.project.controller;

import com.gmz.project.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("/")
    public String test(){
        /*String blog = null;
        if(blog == null){
            throw new NotFoundException("博客找不到");
        }*/
        int i = 9/0;
        return "index";
    }
}
