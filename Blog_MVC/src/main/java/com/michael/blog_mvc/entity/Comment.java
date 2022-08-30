package com.michael.blog_mvc.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "tbl_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    private String content;
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
