package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.repositories.OrderRepository;
import com.petiveriaalliacea.taza.services.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order addNewOrder(Order newOrder) {
        return orderRepository.save(newOrder);
    }

    @Override
    public String deleteOrder(Long id) {
        if(orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Category not found!";
    }


//    @Override
//    public List<Order> getUserOrders(Long userId) {
//        return orderRepository.findByUserId(userId);
//    }
//
//    @Override
//    public List<Order> getCompanyOrders(Long companyId) {
//        return orderRepository.findByCompanyId(companyId);
//    }

}
