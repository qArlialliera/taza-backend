package com.petiveriaalliacea.taza.services;


import com.petiveriaalliacea.taza.dto.ReviewDto;

import java.util.List;

public interface IReviewService {
    List<ReviewDto> getReviews();
    List<ReviewDto> getReviewsOfCompany(Long companyId);

    List<ReviewDto> getReviewsOfCompanyAndUser(Long companyId, Long userId);

    ReviewDto getReview(Long id);

    Double getRatingOfCompany(Long companyId);

    ReviewDto addNewReview(ReviewDto reviewDto);
    ReviewDto editReview(Long id, ReviewDto reviewDto);
    String deleteReview(Long id);
}
