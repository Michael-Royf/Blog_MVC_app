package com.michael.blog_mvc.service.impl;

import com.michael.blog_mvc.entity.Comment;
import com.michael.blog_mvc.entity.Post;
import com.michael.blog_mvc.entity.User;
import com.michael.blog_mvc.mapper.CommentMapper;
import com.michael.blog_mvc.payload.request.CommentRequest;
import com.michael.blog_mvc.payload.response.CommentDto;
import com.michael.blog_mvc.repository.CommentRepository;
import com.michael.blog_mvc.repository.PostRepository;
import com.michael.blog_mvc.repository.UserRepository;
import com.michael.blog_mvc.service.CommentService;
import com.michael.blog_mvc.util.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createComment(String postUrl, CommentRequest commentRequest) {
        Post post = postRepository.findByUrl(postUrl).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = Comment.builder()
                .name(commentRequest.getName())
                .email(commentRequest.getEmail())
                .content(commentRequest.getContent())
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> findCommentByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByEmail(email);
        Long userId = user.getId();
        List<Comment> comments = commentRepository.findByCommentsByPost(userId);
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
    }


}
