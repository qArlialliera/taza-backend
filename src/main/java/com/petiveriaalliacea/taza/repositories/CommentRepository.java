package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Comment;
import com.petiveriaalliacea.taza.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByReview(Review review);
    List<Comment> findAllByReviewId(Long id);

}
