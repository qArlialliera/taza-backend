package com.petiveriaalliacea.taza.services;


import com.petiveriaalliacea.taza.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    List<OrderDto> getAllOrders();
//    List<Order> getUserOrders(Long userId);
//    List<Order> getCompanyOrders(Long companyId);
    OrderDto getOrder(Long id);

    OrderDto addNewOrder(String token, OrderDto orderDto);

    OrderDto editOrder(Long id, OrderDto orderDto);
    String deleteOrder(Long id);

    List<OrderDto> getUserOrders(Long userId);

    int getUserOrdersCount(Long userId);

    List<OrderDto> getCompanyOrders(Long companyId);
}
