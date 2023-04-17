package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.ReviewDto;
import com.petiveriaalliacea.taza.entities.Review;
import com.petiveriaalliacea.taza.repositories.ReviewRepository;
import com.petiveriaalliacea.taza.services.IReviewService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;
    private final Mapper mapper;

    @Override
    public List<ReviewDto> getReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDto> reviewsDtos = reviews
                .stream()
                .map(mapper::toReviewDto)
                .collect(Collectors.toList());
        return reviewsDtos;
    }

    @Override
    public List<ReviewDto> getReviewsOfCompany(Long companyId) {
        List<Review> reviews = reviewRepository.findReviewsByCompany_Id(companyId).get();
        return reviews.stream().map(mapper::toReviewDto).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getReviewsOfCompanyAndUser(Long companyId, Long userId) {
        List<Review> reviews = reviewRepository.findReviewsByCompany_IdAndUser_Id(companyId,userId).get();
        return reviews.stream().map(mapper::toReviewDto).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReview(Long id) {
        Review review = reviewRepository.getReferenceById(id);
        return mapper.toReviewDto(review);
    }
    @Override
    public Double getRatingOfCompany(Long companyId) {
        List<Review> reviews = reviewRepository.findReviewsByCompany_Id(companyId).get();
        double sum = 0;
        for(Review review : reviews){
            sum += review.getRate();
        }
        return sum/reviews.size();
    }


    @Override
    public ReviewDto addNewReview(ReviewDto reviewDto) {
        Review review = mapper.toReview(reviewDto);
        review.setCompany(reviewDto.getCompany());
        review.setUser(reviewDto.getUser());
        return mapper.toReviewDto(reviewRepository.save(review));
    }

    @Override
    public ReviewDto editReview(Long id, ReviewDto reviewDto) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            if (!StringUtils.isEmpty(reviewDto.getComment())) {
                review.get().setComment(reviewDto.getComment());
            }
            if (reviewDto.getRate() != review.get().getRate()) {
                review.get().setRate(reviewDto.getRate());
            }
        }
        return mapper.toReviewDto(reviewRepository.save(review.get()));

    }

    @Override
    public String deleteReview(Long id) {
        if(reviewRepository.findById(id).isPresent()) {
            reviewRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Review not found!";
    }
}
