package com.michael.blog_mvc.controller;

import com.michael.blog_mvc.payload.request.PostRequest;
import com.michael.blog_mvc.payload.response.CommentDto;
import com.michael.blog_mvc.payload.response.PostDto;
import com.michael.blog_mvc.service.CommentService;
import com.michael.blog_mvc.service.PostService;
import com.michael.blog_mvc.util.ROLE;
import com.michael.blog_mvc.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/admin/posts")
    public String posts(Model model) {
        String role = SecurityUtils.getRole();
        List<PostDto> posts = null;
        if (ROLE.ROLE_ADMIN.name().equals(role)) {
            posts = postService.findAllPosts();
        } else {
            posts = postService.findPostsByUser();
        }
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }


    @GetMapping("/admin/posts/comments")
    public String postComments(Model model) {
        String role = SecurityUtils.getRole();
        List<CommentDto> comments = null;
        if (ROLE.ROLE_ADMIN.name().equals(role)) {
            comments = commentService.findAllComments();
        } else {
            comments = commentService.findCommentByPost();
        }
        model.addAttribute("comments", comments);
        return "admin/comments";
    }

    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }

    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model) {
        PostRequest postRequest = new PostRequest();
        model.addAttribute("post", postRequest);
        return "/admin/create_post";
    }

    //form submit request
    @PostMapping("/admin/posts")
    public String createPost(@ModelAttribute("post") @Valid PostRequest postRequest,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("post", postRequest);
            return "admin/create_post";
        }
        postRequest.setUrl(getURl(postRequest.getTitle()));
        postService.creatPost(postRequest);
        return "redirect:/admin/posts";
    }


    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable Long postId, Model model) {
        PostDto postDto = postService.findByPostId(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }


    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable Long postId,
                             @ModelAttribute("post") @Valid PostRequest postRequest,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("post", postRequest);
            return "admin/edit_post";
        }
        //    postRequest.setId(postId);
        postService.updatePost(postId, postRequest);
        return "redirect:/admin/posts";
    }


    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }

    @GetMapping("/admin/posts/search")
    public String searchPost(@RequestParam(value = "query") String query, Model model) {
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }


    private static String getURl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }


}
