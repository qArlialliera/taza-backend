package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.OrderDto;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;
import com.petiveriaalliacea.taza.repositories.OrderRepository;
import com.petiveriaalliacea.taza.services.IOrderService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;
    private final Mapper mapper;
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
    public OrderDto editOrder(Long id, OrderDto orderDto) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            if (!StringUtils.isEmpty(orderDto.getAddress())) {
                order.get().setAddress(orderDto.getAddress());
            }
            if (!StringUtils.isEmpty(orderDto.getComment())) {
                order.get().setComment(orderDto.getComment());
            }
            if (orderDto.getDate() != order.get().getDate() && orderDto.getDate() != null) {
                order.get().setDate(order.get().getDate());
            }
            if (orderDto.getArea() != order.get().getArea()) {
                order.get().setArea(order.get().getArea());
            }
            if (orderDto.getRooms() != order.get().getRooms()) {
                order.get().setRooms(order.get().getRooms());
            }
            if (orderDto.getStatus() != order.get().getStatus()) {
                order.get().setStatus(order.get().getStatus());
            }
            if (orderDto.getAdditionalServices() != order.get().getAdditionalServices()) {
                order.get().setAdditionalServices(order.get().getAdditionalServices());
            }
            if (orderDto.getCompanyService() != order.get().getCompanyService()) {
                order.get().setCompanyService(order.get().getCompanyService());
            }
        }
        return mapper.toOrderDto(order.get());
    }
    @Override
    public String deleteOrder(Long id) {
        if(orderRepository.findById(id).isPresent()) {
            orderRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Category not found!";
    }


    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findAllByUser_Id(userId).get();
    }

    @Override
    public List<Order> getCompanyOrders(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        return orderRepository.findAllByCompanyService_Company(company).get();
    }

}
