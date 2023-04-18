package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.dto.UserRequestDto;
import com.petiveriaalliacea.taza.entities.User;
import com.petiveriaalliacea.taza.services.impl.OrderService;
import com.petiveriaalliacea.taza.services.impl.ReviewService;
import com.petiveriaalliacea.taza.services.impl.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.petiveriaalliacea.taza.utils.Constants.PRIVATE_API_ENDPOINT;

@RestController
@RequestMapping(PRIVATE_API_ENDPOINT + "/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final ReviewService reviewService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user-details")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok().body(userService.getUser());
    }

    @PutMapping("/edit/profile")
    public ResponseEntity editUser(@RequestBody UserRequestDto userEdit) {
        return ResponseEntity.ok(userService.editUser(userEdit));
    }

    @DeleteMapping("/delete/profile")
    private ResponseEntity deleteCompany() {
        return ResponseEntity.ok(userService.deleteUser());
    }

    @PutMapping("/role/make-admin/{id}")
    public ResponseEntity<?> addAdminRole(@PathVariable Long id) {
        return ResponseEntity.ok(userService.addAdminRole(id));
    }

    @PutMapping("/photo/upload/{uuid}")
    public void uploadPhoto(@PathVariable("uuid") UUID photo,
                            @RequestHeader("Authorization") @Parameter(hidden = true) String token) {
        userService.addPhoto(photo, token);
    }

    @GetMapping("/photo/get")
    public ResponseEntity<UUID> getPhoto(@RequestHeader("Authorization") @Parameter(hidden = true) String token) {
        return ResponseEntity.ok(userService.getPhoto(token));
    }

    @GetMapping("/orders/count/{id}")
    public ResponseEntity<Integer> getOrdersCount(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getUserOrdersCount(id));
    }

    @GetMapping("/reviews/count/{id}")
    public ResponseEntity<Integer> getReviewsCount(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsCountOfUser(id));
    }
}
