package com.lakshit.LinkedInProject.PostService.service;

import com.lakshit.LinkedInProject.PostService.entity.Post;
import com.lakshit.LinkedInProject.PostService.entity.PostLike;
import com.lakshit.LinkedInProject.PostService.exception.BadRequestException;
import com.lakshit.LinkedInProject.PostService.exception.ResourceNotFoundException;
import com.lakshit.LinkedInProject.PostService.repository.PostLikeRepository;
import com.lakshit.LinkedInProject.PostService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void likePost(Long postId) {
        Long userId = 1L;
        log.info("liking the post id: {} with user id: {}", postId, userId);

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id: "+postId));

        boolean hasPostAlreadyLiked;
        hasPostAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);

        if(hasPostAlreadyLiked){
            throw new BadRequestException("You can not like the post again");
        }else{
            PostLike postLike = new PostLike();
            postLike.setPostId(postId);
            postLike.setUserId(userId);
            postLikeRepository.save(postLike);
        }
    }

    @Transactional
    public void unLikePost(Long postId) {
        Long userId = 1L;
        log.info("unLiking the post id: {} with user id: {}", postId, userId);

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id: "+postId));

        boolean hasPostAlreadyUnLiked;
        hasPostAlreadyUnLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);

        if(!hasPostAlreadyUnLiked){
            throw new BadRequestException("You can not unLike the post that you has not like");
        }else{
            postLikeRepository.deleteByUserIdAndPostId(userId,postId);
        }
    }
}
