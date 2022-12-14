package com.michael.blog_mvc.repository;

import com.michael.blog_mvc.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select c.* from tbl_comments c inner join tbl_posts p\n" +
            "where c.post_id = p.id and p.created_by =:userId", nativeQuery = true)
    List<Comment> findByCommentsByPost(Long userId);
}
