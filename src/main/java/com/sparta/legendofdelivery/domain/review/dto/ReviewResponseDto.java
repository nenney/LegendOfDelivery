package com.sparta.legendofdelivery.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponseDto {

  private final Long reviewId;
  private final String content;
  private final LocalDateTime createAt;
  private final LocalDateTime modifiedAt;

  @QueryProjection
  public ReviewResponseDto(Long reviewId, String content, LocalDateTime createAt,
      LocalDateTime modifiedAt) {
    this.reviewId = reviewId;
    this.content = content;
    this.createAt = createAt;
    this.modifiedAt = modifiedAt;
  }

}
