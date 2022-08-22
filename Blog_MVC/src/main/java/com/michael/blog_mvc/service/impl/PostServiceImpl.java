package com.michael.blog_mvc.service.impl;

import com.michael.blog_mvc.payload.response.PostDto;
import com.michael.blog_mvc.repository.PostRepository;
import com.michael.blog_mvc.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;


    @Override
    public List<PostDto> findAllPosts() {
        return postRepository.findAll().stream().
                map(post -> mapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
