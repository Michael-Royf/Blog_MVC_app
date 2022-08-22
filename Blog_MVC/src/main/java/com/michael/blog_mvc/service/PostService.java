package com.michael.blog_mvc.service;

import com.michael.blog_mvc.payload.response.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
}
