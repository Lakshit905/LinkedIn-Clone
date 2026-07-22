package com.lakshit.LinkedInProject.PostService.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long Id;

    private String content;

    private Long userId;

    private LocalDateTime createdAt;
}
