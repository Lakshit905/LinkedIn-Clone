package com.lakshit.LinkedInProject.PostService.controller;

import com.lakshit.LinkedInProject.PostService.dto.PostDto;
import com.lakshit.LinkedInProject.PostService.dto.PostRequestBodyDto;
import com.lakshit.LinkedInProject.PostService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostRequestBodyDto postRequestBodyDto
            , HttpServletRequest httpServletRequest){
        PostDto postDto = postService.createPost(postRequestBodyDto,1L);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostsForUser (@PathVariable Long userId){
        List<PostDto> postDtoList = postService.getAllPostsOfUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
}
