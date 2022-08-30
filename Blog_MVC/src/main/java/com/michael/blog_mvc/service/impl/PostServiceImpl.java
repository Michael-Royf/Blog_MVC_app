package com.michael.blog_mvc.service.impl;

import com.michael.blog_mvc.entity.Post;
import com.michael.blog_mvc.entity.User;
import com.michael.blog_mvc.mapper.PostMapper;
import com.michael.blog_mvc.payload.request.PostRequest;
import com.michael.blog_mvc.payload.response.CommentDto;
import com.michael.blog_mvc.payload.response.PostDto;
import com.michael.blog_mvc.repository.PostRepository;
import com.michael.blog_mvc.repository.UserRepository;
import com.michael.blog_mvc.service.PostService;
import com.michael.blog_mvc.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostDto> findAllPosts() {
        return postRepository.findAll().stream().
                map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void creatPost(PostRequest postRequest) {
        String email = SecurityUtils.getCurrentUser().getUsername();

        User user = userRepository.findByEmail(email);
        Post post = mapper.map(postRequest, Post.class);
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public PostDto findByPostId(Long postId) {
        Post postDb = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostMapper.mapToPostDto(postDb);
    }

    @Override
    public void updatePost(Long postId, PostRequest postRequest) {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContent(postRequest.getContent());
        post.setTitle(postRequest.getTitle());
        post.setShortDescription(postRequest.getShortDescription());
        post.setCreatedBy(user);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostMapper.mapToPostDto(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        return postRepository.searchPost(query)
                .stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByUser() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Post> posts = postRepository.findPostsByUser(userId);
        return posts.stream()
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }


}
