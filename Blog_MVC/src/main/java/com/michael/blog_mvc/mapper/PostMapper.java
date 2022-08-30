package com.michael.blog_mvc.mapper;

import com.michael.blog_mvc.entity.Post;
import com.michael.blog_mvc.payload.response.PostDto;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .content(post.getContent())
                .shortDescription(post.getShortDescription())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .comments(post.getComments()
                        .stream().map(CommentMapper::mapToCommentDto)
                        .collect(Collectors.toSet()))
                .build();
    }


    public static Post mapToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .url(postDto.getUrl())
                .shortDescription(postDto.getShortDescription())
                .createDate(postDto.getCreateDate())
                .updateDate(postDto.getUpdateDate())
                .build();
    }




}
