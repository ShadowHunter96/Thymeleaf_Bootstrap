package com.example.thymeleaf2.user.service;

import com.example.thymeleaf2.role.entity.Role;
import com.example.thymeleaf2.role.repository.RoleRepository;
import com.example.thymeleaf2.user.dto.UserDto;
import com.example.thymeleaf2.user.entity.User;
import com.example.thymeleaf2.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by User: Vu
 * Date: 06.07.2024
 * Time: 15:49
 */
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user==null){
            return (UserDetails) new RuntimeException("user not fouind");
        }
        return new org.springframework.security.core.userdetails.User (user.getUserName(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<?extends GrantedAuthority> mapRolesToAuthorities(Collection<Role>roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public User save(UserDto adminDto){
        User admin = new User();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUserName(adminDto.getUserName());
        String encodedPassword = bCryptPasswordEncoder.encode(adminDto.getPassword());
        admin.setPassword(encodedPassword);
        admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));

        userRepository.save(admin);
        return userRepository.findByUserName(adminDto.getUserName());
    }

    public User findByUserName(String userName){
        User foundUser = userRepository.findByUserName(userName);
        return foundUser;
    }


}
