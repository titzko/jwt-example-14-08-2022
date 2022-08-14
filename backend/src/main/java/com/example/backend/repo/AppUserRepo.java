package com.example.backend.repo;

import com.example.backend.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
}
