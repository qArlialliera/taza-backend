package com.petiveriaalliacea.taza.services;


import com.petiveriaalliacea.taza.dto.OrderDto;
import com.petiveriaalliacea.taza.entities.OrderStatus;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getAllOrders();
    OrderDto getOrder(Long id);

    OrderDto addNewOrder(String token, OrderDto orderDto);

    OrderDto editOrder(Long id, OrderDto orderDto);

    OrderDto changeStatus(String token, Long id, OrderStatus status);

    String deleteOrder(Long id);

    List<OrderDto> getUserOrders(Long userId);

    int getUserOrdersCount(Long userId);

    List<OrderDto> getCompanyOrders(Long companyId);
}
