package com.michael.blog_mvc.controller;

import com.michael.blog_mvc.payload.request.CommentRequest;
import com.michael.blog_mvc.payload.response.PostDto;
import com.michael.blog_mvc.service.CommentService;
import com.michael.blog_mvc.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @PostMapping("/{postUrl}/comments")
    public String createComment(@PathVariable("postUrl") String postUrl,
                                @Valid @ModelAttribute("comment") CommentRequest commentRequest,
                                BindingResult result,
                                Model model){

        PostDto postDto = postService.findPostByUrl(postUrl);
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            model.addAttribute("comment", commentRequest);
            return "blog/blog_post";
        }
        commentService.createComment(postUrl, commentRequest);
        return "redirect:/post/" + postUrl;
    }


}
