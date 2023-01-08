package com.petiveriaalliacea.taza.repositories;

import com.petiveriaalliacea.taza.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
