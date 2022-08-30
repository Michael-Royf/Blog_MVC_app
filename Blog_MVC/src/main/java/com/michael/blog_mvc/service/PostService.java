package com.michael.blog_mvc.service;

import com.michael.blog_mvc.payload.request.PostRequest;
import com.michael.blog_mvc.payload.response.CommentDto;
import com.michael.blog_mvc.payload.response.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();

    void creatPost(PostRequest postRequest);

    PostDto findByPostId(Long postId);

    void updatePost(Long postId, PostRequest postRequest);

    void deletePost(Long postId);

    PostDto findPostByUrl(String postUrl);

    List<PostDto> searchPosts(String query);

    List<PostDto> findPostsByUser();



}
