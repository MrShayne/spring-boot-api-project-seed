package com.company.project.service.impl;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.exception.CustomException;
import com.company.project.model.Role;
import com.company.project.model.User;
import com.company.project.security.JwtTokenProvider;
import com.company.project.service.AuthService;
import com.company.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Result register(User userToAdd, List<Role> roles) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andAllEqualTo(userToAdd);
        User userInDb = userService.findByName(userToAdd.getEmail());
        if (userInDb == null) {
            userToAdd.setPassword(passwordEncoder.encode(userToAdd.getEmail()));
            userService.save(userToAdd);
            String token = jwtTokenProvider.createToken(userToAdd.getEmail(), roles);
            return ResultGenerator.genSuccessResult(token);
        } else {
            throw new CustomException("User name is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @Override
    public String login(String username, String password, List<Role> roles) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenProvider.createToken(username, roles);
    }

    @Override
    public Result refresh(String oldToken) {
        return null;
    }
}
