package com.sparta.legendofdelivery.domain.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.legendofdelivery.domain.like.entity.QLike;
import com.sparta.legendofdelivery.domain.review.dto.QReviewResponseDto;
import com.sparta.legendofdelivery.domain.review.dto.ReviewResponseDto;
import com.sparta.legendofdelivery.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.legendofdelivery.domain.like.entity.QLike.like;
import static com.sparta.legendofdelivery.domain.review.entity.QReview.review;

@RequiredArgsConstructor
@Repository
public class LikeRepositoryCustomImpl implements LikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ReviewResponseDto> findLikedReviewsByUser(User user, Pageable pageable) {

        if (user == null) {
            return Page.empty(pageable);
        }

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
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public int countLikedReviewsByUser(Long userId) {
        QLike qLike = like;

        Long count = jpaQueryFactory
                .select(qLike.count())
                .from(qLike)
                .where(qLike.user.id.eq(userId))
                .fetchOne();

        return count != null ? count.intValue() : 0;
    }
}