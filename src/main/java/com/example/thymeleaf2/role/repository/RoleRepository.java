package com.example.thymeleaf2.role.repository;

import com.example.thymeleaf2.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User: Vu
 * Date: 06.07.2024
 * Time: 16:14
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
