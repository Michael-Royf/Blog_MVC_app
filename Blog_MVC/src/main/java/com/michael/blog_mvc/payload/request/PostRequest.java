package com.michael.blog_mvc.payload.request;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PostRequest {
    private String title;
    private String url;
    private String content;
    private String shortDescription;
}
