package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.entities.Order;
import com.petiveriaalliacea.taza.services.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addNewOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.addNewOrder(order));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
//        return ResponseEntity.ok(orderService.getUserOrders(userId));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<List<Order>> getCompanyOrders(@PathVariable Long companyId) {
//        return ResponseEntity.ok(orderService.getCompanyOrders(companyId));
//    }
}
