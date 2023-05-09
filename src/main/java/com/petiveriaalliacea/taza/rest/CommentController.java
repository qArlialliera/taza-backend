package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.CommentDto;
import com.petiveriaalliacea.taza.services.impl.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping( PRIVATE_API_ENDPOINT + "/comments")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }
    @GetMapping("/all/{reviewId}")
    public ResponseEntity<List<CommentDto>> getCommentsByReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(commentService.getCommentsOfReview(reviewId));
    }

    @PostMapping("/add")
    public ResponseEntity<CommentDto> addNewComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.addComment(commentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> editComment(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        return ResponseEntity.ok(commentService.editComment(id, commentDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }
}
