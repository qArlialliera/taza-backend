package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.CommentDto;
import com.petiveriaalliacea.taza.entities.Comment;
import com.petiveriaalliacea.taza.repositories.CommentRepository;
import com.petiveriaalliacea.taza.services.ICommentService;
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
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final Mapper mapper;
    @Override
    public List<CommentDto> getAllComments(){
        return commentRepository.findAll().stream().map(mapper::toCommentDto).collect(Collectors.toList());
    }
    @Override
    public CommentDto getComment(Long id){
        return mapper.toCommentDto(commentRepository.findById(id).get());
    }
    @Override
    public List<CommentDto> getCommentsOfReview(Long reviewId){
        List<Comment> comments = commentRepository.findAllByReviewId(reviewId);
        return comments.stream().map(mapper::toCommentDto).collect(Collectors.toList());
    }
    @Override
    public CommentDto addComment(CommentDto dto){
        Comment comment = mapper.toComment(dto);
        return mapper.toCommentDto(commentRepository.save(comment));
    }
    @Override
    public CommentDto editComment(Long id, CommentDto dto){
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()){
            if (!StringUtils.isEmpty(dto.getText())) {
                comment.get().setText(dto.getText());
            }
        }
        return mapper.toCommentDto(commentRepository.save(comment.get()));
    }
    @Override
    public String deleteComment(Long id){
        if(commentRepository.findById(id).isPresent()){
            commentRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Comment not found!";
    }


}
