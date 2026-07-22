package com.lakshit.LinkedInProject.PostService.service;

import com.lakshit.LinkedInProject.PostService.dto.PostDto;
import com.lakshit.LinkedInProject.PostService.dto.PostRequestBodyDto;
import com.lakshit.LinkedInProject.PostService.entity.Post;
import com.lakshit.LinkedInProject.PostService.exception.ResourceNotFoundException;
import com.lakshit.LinkedInProject.PostService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public PostDto createPost(PostRequestBodyDto postRequestBodyDto,Long userId) {
        log.info("Creating post with user id: {}",userId);
        Post post = modelMapper.map(postRequestBodyDto,Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post,PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.info("getting post with id : {}",postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with post id : "+ postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("getting all posts with user id : {}", userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList.stream().map((element) -> modelMapper.map(element, PostDto.class)).collect(Collectors.toList());
    }
}
