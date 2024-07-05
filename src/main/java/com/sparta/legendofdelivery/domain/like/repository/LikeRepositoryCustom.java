package com.sparta.legendofdelivery.domain.like.repository;

import com.sparta.legendofdelivery.domain.review.dto.ReviewResponseDto;
import com.sparta.legendofdelivery.domain.user.entity.User;
import jakarta.persistence.OneToMany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeRepositoryCustom {
    Page<ReviewResponseDto> findLikedReviewsByUser(User user, Pageable pageable);
    int countLikedReviewsByUser(Long userId);
}