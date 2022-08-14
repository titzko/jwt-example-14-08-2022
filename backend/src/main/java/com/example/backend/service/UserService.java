package com.example.backend.service;

import com.example.backend.domain.AppUser;
import com.example.backend.domain.Role;

import java.util.List;

public interface UserService {

    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToAppUser(String username, String roleName);
    AppUser gerAppUser(String username);
    List<AppUser> getUsers();

}
