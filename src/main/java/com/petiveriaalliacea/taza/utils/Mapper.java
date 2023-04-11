package com.petiveriaalliacea.taza.utils;

import com.petiveriaalliacea.taza.dto.CompanyRequestDto;
import com.petiveriaalliacea.taza.dto.OrderDto;
import com.petiveriaalliacea.taza.dto.ReviewDto;
import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.entities.Review;
import com.petiveriaalliacea.taza.entities.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface Mapper {
    User toUser(UserRequestDto dto);

    Company toCompany(CompanyRequestDto dto);

    Review toReview(ReviewDto dto);

    ReviewDto toReviewDto(Review review);

    Order toOrder(OrderDto dto);

    OrderDto toOrderDto(Order order);


}
