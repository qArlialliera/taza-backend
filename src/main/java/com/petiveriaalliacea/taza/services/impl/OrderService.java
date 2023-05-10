package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.dto.OrderDto;
import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.entities.OrderStatus;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.entities.chat.ChatMessage;
import com.petiveriaalliacea.taza.entities.chat.MessageStatus;
import com.petiveriaalliacea.taza.repositories.CompanyRepository;
import com.petiveriaalliacea.taza.repositories.OrderRepository;
import com.petiveriaalliacea.taza.repositories.UserRepository;
import com.petiveriaalliacea.taza.security.JwtUtils;
import com.petiveriaalliacea.taza.services.IOrderService;
import com.petiveriaalliacea.taza.utils.Mapper;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService implements IOrderService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final Mapper mapper;
    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(mapper::toOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrder(Long id) {
        return mapper.toOrderDto(orderRepository.findById(id).get());
    }

    @Override
    public OrderDto addNewOrder(String token, OrderDto orderDto) {
        User user = userRepository.findByUsername(JwtUtils.getUsername(token))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        Order order = mapper.toOrder(orderDto);
        Optional<Company> company = orderDto.getCompanyService().getCompany().stream().findFirst();
        User compRep = company.get().getUser();
        var chatId = chatRoomService.getChatId(user.getId(), compRep.getId());
        String content = "Новый заказ профессиональной уборки помещения.\nДата и время: " + order.getDate() + "\nПлощадь: " +order.getArea()+" кв. \nКол. комнат: " + order.getRooms();
        ChatMessage message = new ChatMessage(chatId.get(), user.getId(), compRep.getId(), user.getUsername(), compRep.getUsername(),content, Date.from(Instant.now()), MessageStatus.DELIVERED);
        ChatMessage sentMessage = chatMessageService.save(message);
        messagingTemplate.convertAndSendToUser(sentMessage.getRecipientName(), "/private", sentMessage); // /user/David/private
        return mapper.toOrderDto(orderRepository.save(order));
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
                order.get().setDate(orderDto.getDate());
            }
            if (orderDto.getArea() != order.get().getArea()) {
                order.get().setArea(orderDto.getArea());
            }
            if (orderDto.getRooms() != order.get().getRooms()) {
                order.get().setRooms(orderDto.getRooms());
            }
            if (orderDto.getAdditionalServices() != order.get().getAdditionalServices()) {
                order.get().setAdditionalServices(orderDto.getAdditionalServices());
            }
            if (orderDto.getCompanyService() != order.get().getCompanyService()) {
                order.get().setCompanyService(orderDto.getCompanyService());
            }

        }
        return mapper.toOrderDto(orderRepository.save(order.get()));
    }
    @Override
    public OrderDto changeStatus(String token, Long id, OrderStatus status){
        User user = userRepository.findByUsername(JwtUtils.getUsername(token))
                .orElseThrow(() -> new IllegalArgumentException("Invalid user!"));
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            order.get().setStatus(status);
        }
        Order changedOrder = orderRepository.save(order.get());
        String content = "Заказ в обработке. \nДетали: \nДата и время: " + order.get().getDate() + "\nПлощадь: " +order.get().getArea()+" кв. \nКол. комнат: " + order.get().getRooms();
        if (status.getId() == 2)
            content = "Заказ в процессе. \nДетали: \nДата и время: " + order.get().getDate() + "\nПлощадь: " +order.get().getArea()+" кв. \nКол. комнат: " + order.get().getRooms();
        else if (status.getId() == 3)
            content = "Заказ выполнен. \nДетали: \nДата и время: " + order.get().getDate() + "\nПлощадь: " +order.get().getArea()+" кв. \nКол. комнат: " + order.get().getRooms();
        else if (status.getId() == 4)
            content = "Закак отменён. \nДетали: \nДата и время: " + order.get().getDate() + "\nПлощадь: " +order.get().getArea()+" кв. \nКол. комнат: " + order.get().getRooms();
        var chatId = chatRoomService.getChatId(user.getId(), order.get().getUser().getId());
        ChatMessage message = new ChatMessage(chatId.get(), user.getId(), order.get().getUser().getId(), user.getUsername(), order.get().getUser().getUsername(), content, Date.from(Instant.now()), MessageStatus.DELIVERED);
        ChatMessage sentMessage = chatMessageService.save(message);
        messagingTemplate.convertAndSendToUser(sentMessage.getRecipientName(), "/private", sentMessage); // /user/David/private
        return mapper.toOrderDto(changedOrder);
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
    public List<OrderDto> getUserOrders(Long userId) {
        return orderRepository.findAllByUser_Id(userId).get().stream().map(mapper::toOrderDto).collect(Collectors.toList());
    }

    @Override
    public int getUserOrdersCount(Long userId) {
        return orderRepository.findAllByUser_Id(userId).get().size();
    }

    @Override
    public List<OrderDto> getCompanyOrders(Long companyId) {
        Company company = companyRepository.findById(companyId).get();
        return orderRepository.findAllByCompanyService_Company(company).get().stream().map(mapper::toOrderDto).collect(Collectors.toList());
    }

}
