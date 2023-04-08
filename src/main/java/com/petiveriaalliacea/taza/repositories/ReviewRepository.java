package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<List<Review>> findReviewsByCompany_Id(Long companyId);
    Optional<List<Review>> findReviewsByUser_Id(Long userId);
    Optional<List<Review>> findReviewsByCompany_IdAndUser_Id(Long companyId, Long userId);

}
