package com.sparta.legendofdelivery.domain.like.repository;

import com.sparta.legendofdelivery.domain.like.entity.Like;
import com.sparta.legendofdelivery.domain.review.dto.ReviewResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findLikeByReviewIdAndUserId(Long reviewId, Long userId);
}
