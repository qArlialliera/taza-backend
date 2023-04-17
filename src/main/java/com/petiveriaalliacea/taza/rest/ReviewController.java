package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.ReviewDto;
import com.petiveriaalliacea.taza.services.impl.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;
import static com.petiveriaalliacea.taza.utils.Constants.PUBLIC_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/review")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDto>> getReviews(){
        return ResponseEntity.ok().body(reviewService.getReviews());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getReview(id));
    }
    @GetMapping("company/{id}")
    public ResponseEntity<List<ReviewDto>> getReviewsOfCompany(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getReviewsOfCompany(id));
    }
    @GetMapping("rating/{id}")
    public ResponseEntity<Double> getRatingOfCompany(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getRatingOfCompany(id));
    }
    @PostMapping("/add")
    public ResponseEntity<ReviewDto> addNewReview(@RequestBody ReviewDto reviewDtoDto){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reviews/add").toUriString());
        return ResponseEntity.created(uri).body(reviewService.addNewReview(reviewDtoDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> editReview(@RequestBody ReviewDto reviewDtoDto, @PathVariable Long id){
        return ResponseEntity.ok(reviewService.editReview(id, reviewDtoDto));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity deleteReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }
}
