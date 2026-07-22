package com.lakshit.LinkedInProject.PostService.controller;

import com.lakshit.LinkedInProject.PostService.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId){
        postLikeService.likePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unLikePost(@PathVariable Long postId){
        postLikeService.unLikePost(postId);
        return ResponseEntity.noContent().build();
    }
}
