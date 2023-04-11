package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Company;
import com.petiveriaalliacea.taza.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);

    Optional<List<Order>> findAllByUser_Id(Long id);
    Optional<List<Order>> findAllByCompanyService_Company(Company company);

}
