package com.michael.blog_mvc.mapper;

import com.michael.blog_mvc.entity.Comment;
import com.michael.blog_mvc.payload.response.CommentDto;

public class CommentMapper {
    public static CommentDto mapToCommentDto(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .content(comment.getContent())
                .createDate(comment.getCreateDate())
                .updateDate(comment.getUpdateDate())
                .build();
    }


    public static Comment mapToComment(CommentDto commentDto){
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .content(commentDto.getContent())
                .createDate(commentDto.getCreateDate())
                .updateDate(commentDto.getUpdateDate())
                .build();
    }


}
