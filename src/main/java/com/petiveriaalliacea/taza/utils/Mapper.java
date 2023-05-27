package com.petiveriaalliacea.taza.utils;

import com.petiveriaalliacea.taza.dto.*;
import com.petiveriaalliacea.taza.entities.*;
import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import org.mapstruct.InjectionStrategy;


@org.mapstruct.Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface Mapper {

    User toUser(UserRequestDto dto);

    UserResponseDto toUserResponseDto(User user);
    UserViewDto toUserViewDto(User user);

    Company toCompany(CompanyRequestDto dto);

    CompanyDto toCompanyDto(Company company);
    CompanyResponseDto toCompanyResponseDto(Company company);

    Review toReview(ReviewDto dto);

    ReviewDto toReviewDto(Review review);

    Order toOrder(OrderDto dto);

    OrderDto toOrderDto(Order order);

    ChatMessage toChatMessage(ChatMessageDto dto);
    ChatMessageDto toChatMessageDto(ChatMessage chatMessage);

    Comment toComment(CommentDto dto);
    CommentDto toCommentDto(Comment comment);

}
