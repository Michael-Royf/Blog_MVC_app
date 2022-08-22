package com.michael.blog_mvc.controller;

import com.michael.blog_mvc.payload.response.PostDto;
import com.michael.blog_mvc.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller

//@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }





}
