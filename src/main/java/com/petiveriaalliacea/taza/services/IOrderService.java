package com.petiveriaalliacea.taza.services;


import com.petiveriaalliacea.taza.dto.OrderDto;
import com.petiveriaalliacea.taza.entities.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
//    List<Order> getUserOrders(Long userId);
//    List<Order> getCompanyOrders(Long companyId);
    Order getOrder(Long id);

    OrderDto addNewOrder(String token, OrderDto orderDto);

    OrderDto editOrder(Long id, OrderDto orderDto);
    String deleteOrder(Long id);

    List<Order> getUserOrders(Long userId);

    int getUserOrdersCount(Long userId);

    List<Order> getCompanyOrders(Long companyId);
}
