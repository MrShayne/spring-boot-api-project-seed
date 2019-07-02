package com.company.project.service;

import com.company.project.core.Result;
import com.company.project.model.Role;
import com.company.project.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthService {
    // now there is no a lot of roles, so I will assign role by where they login
    Result register(User userToAdd, List<Role> roles);
    String login(String username, String password, List<Role> roles);
    Result refresh(String oldToken);
}
