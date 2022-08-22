package com.michael.blog_mvc.payload.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PostDto {
    private Long id;
    private String title;
    private String url;
    private String content;
    private String shortDescription;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
