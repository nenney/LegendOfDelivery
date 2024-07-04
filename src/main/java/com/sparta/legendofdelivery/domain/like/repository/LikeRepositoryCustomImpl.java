package com.sparta.legendofdelivery.domain.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.legendofdelivery.domain.review.dto.QReviewResponseDto;
import com.sparta.legendofdelivery.domain.review.dto.ReviewResponseDto;
import com.sparta.legendofdelivery.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.sparta.legendofdelivery.domain.like.entity.QLike.like;
import static com.sparta.legendofdelivery.domain.review.entity.QReview.review;

@RequiredArgsConstructor
@Component
public class LikeRepositoryCustomImpl implements LikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewResponseDto> findLikedReviewsByUser(User user, Pageable pageable) {
        List<ReviewResponseDto> content = queryFactory
                .select(new QReviewResponseDto(
                        review.id,
                        review.content,
                        review.createAt,
                        review.modifiedAt
                ))
                .from(like)
                .join(like.review, review)
                .where(like.user.eq(user))
                .orderBy(review.createAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(like)
                .where(like.user.eq(user))
                .fetchCount();

        return new PageImpl<>(content, pageable, total);
    }
}