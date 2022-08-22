package com.michael.blog_mvc.controller;

import com.michael.blog_mvc.payload.response.PostDto;
import com.michael.blog_mvc.service.PostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/admin/posts")
    public String getAllPosts(Model model){
        List<PostDto> posts = postService.findAllPosts();
       // return  new ResponseEntity<List<PostDto>>(postService.findAllPosts(), HttpStatus.OK);
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }





}
