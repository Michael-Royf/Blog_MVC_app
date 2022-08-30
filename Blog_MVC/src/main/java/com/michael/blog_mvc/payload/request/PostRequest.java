package com.michael.blog_mvc.payload.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PostRequest {
    private Long id;
    @NotEmpty(message = "Post title should not be empty ")
    private String title;

    private String url;
    @NotEmpty(message = "Post content should not be empty" )
    private String content;
    @NotEmpty(message = "Post short description should not be empty")
    private String shortDescription;
}
