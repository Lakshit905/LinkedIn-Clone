package com.lakshit.LinkedInProject.PostService.repository;

import com.lakshit.LinkedInProject.PostService.dto.PostDto;
import com.lakshit.LinkedInProject.PostService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
}
