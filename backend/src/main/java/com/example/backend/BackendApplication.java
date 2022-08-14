package com.example.backend;

import com.example.backend.domain.AppUser;
import com.example.backend.domain.Role;
import com.example.backend.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));

            userService.saveAppUser(new AppUser(null, "John Travolta", "john", "1234", new ArrayList<>()));
            userService.saveAppUser(new AppUser(null, "Test Test", "test", "test", new ArrayList<>()));
            userService.saveAppUser(new AppUser(null, "Abc Abc", "abc", "abc", new ArrayList<>()));

            userService.addRoleToAppUser("john", "ROLE_USER");
            userService.addRoleToAppUser("john", "ROLE_MANAGER");
            userService.addRoleToAppUser("john", "ROLE_ADMIN");

            userService.addRoleToAppUser("test", "ROLE_ADMIN");
            userService.addRoleToAppUser("abc", "ROLE_MANAGER");



        };
    }

}
