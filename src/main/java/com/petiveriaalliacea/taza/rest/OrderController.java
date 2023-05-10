package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.OrderDto;
import com.petiveriaalliacea.taza.dto.RequestDto;
import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.entities.OrderStatus;
import com.petiveriaalliacea.taza.services.impl.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/orders")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }
    @PutMapping("/change-status/{id}")
    public ResponseEntity<OrderDto> changeStatus(@RequestHeader("Authorization") @Parameter(hidden = true) String token, @RequestBody OrderStatus status, @PathVariable Long id){
        return ResponseEntity.ok(orderService.changeStatus(token, id, status));
    }
    @PostMapping("/add")
    public ResponseEntity<OrderDto> addNewOrder(@RequestHeader("Authorization") @Parameter(hidden = true) String token, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.addNewOrder(token, orderDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> editOrder(@RequestBody OrderDto orderDto, @PathVariable Long id){
        return ResponseEntity.ok(orderService.editOrder(id, orderDto));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
    @GetMapping("user/{userId}")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }

    @GetMapping("company/{companyId}")
    public ResponseEntity<List<OrderDto>> getCompanyOrders(@PathVariable Long companyId) {
        return ResponseEntity.ok(orderService.getCompanyOrders(companyId));
    }
}
