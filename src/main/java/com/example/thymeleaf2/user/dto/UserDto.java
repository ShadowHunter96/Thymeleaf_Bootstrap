package com.example.thymeleaf2.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by User: Vu
 * Date: 06.07.2024
 * Time: 16:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

}
