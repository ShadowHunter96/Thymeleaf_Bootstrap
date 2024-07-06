package com.example.thymeleaf2.role.entity;

/**
 * Created by User: Vu
 * Date: 06.07.2024
 * Time: 15:40
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by User: Vu
 * Date: 05.07.2024
 * Time: 9:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;
    private String name;
}
