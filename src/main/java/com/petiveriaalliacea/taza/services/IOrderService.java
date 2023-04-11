package com.petiveriaalliacea.taza.services;


import com.petiveriaalliacea.taza.dto.OrderDto;
import com.petiveriaalliacea.taza.entities.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
//    List<Order> getUserOrders(Long userId);
//    List<Order> getCompanyOrders(Long companyId);
    Order getOrder(Long id);
    Order addNewOrder(Order order);
    OrderDto editOrder(Long id, OrderDto orderDto);
    String deleteOrder(Long id);

    List<Order> getUserOrders(Long userId);

    List<Order> getCompanyOrders(Long companyId);
}
