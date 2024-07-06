package com.example.thymeleaf2.user.repository;

import com.example.thymeleaf2.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User: Vu
 * Date: 06.07.2024
 * Time: 15:48
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
