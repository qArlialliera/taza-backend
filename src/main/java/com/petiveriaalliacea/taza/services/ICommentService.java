package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.dto.CommentDto;

import java.util.List;

public interface ICommentService {
    List<CommentDto> getAllComments();

    CommentDto getComment(Long id);

    List<CommentDto> getCommentsOfReview(Long reviewId);

    CommentDto addComment(CommentDto dto);

    CommentDto editComment(Long id, CommentDto dto);

    String deleteComment(Long id);
}
