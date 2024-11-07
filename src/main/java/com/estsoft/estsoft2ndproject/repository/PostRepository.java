package com.estsoft.estsoft2ndproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estsoft.estsoft2ndproject.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByIsActiveTrue();
	List<Post> findByPostTypeAndTargetIdAndIsActiveTrue(String postType, Long targetId);
	List<Post> findByPostTypeAndIsActiveTrue(String postType);
	List<Post> findByUser_UserId(Long userId);
}
