package com.michael.blog_mvc.service;

import com.michael.blog_mvc.payload.request.CommentRequest;
import com.michael.blog_mvc.payload.response.CommentDto;

import java.util.List;

public interface CommentService {

    void createComment(String postUrl, CommentRequest commentRequest);

    List<CommentDto> findAllComments();

    void deleteComment(Long commentId);

    List<CommentDto> findCommentByPost();
}
