package com.company.project.controller;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.exception.CustomException;
import com.company.project.model.Role;
import com.company.project.model.User;
import com.company.project.service.AuthService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
* Created by CodeGenerator on 2019/06/14.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(User user) {
        User userInDb = userService.findByName(user.getEmail());
        if (userInDb != null) {
            user.setId(userInDb.getId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.update(user);
            return ResultGenerator.genSuccessResult();
        } else {
            throw new CustomException("Illegal operation", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/login")
    public Result login(User user){
        String token = authService.login(user.getEmail(), user.getPassword(), Arrays.asList(Role.ROLE_TRAVELER));
        return ResultGenerator.genSuccessResult(token);
    }

    @PostMapping("/admin/login")
    public Result adminLogin(User user) {
        String token = authService.login(user.getEmail(), user.getPassword(), Arrays.asList(Role.ROLE_ADMIN));
        return ResultGenerator.genSuccessResult(token);
    }

    @PostMapping("/register")
    public Result register(User user){
        return authService.register(user, Arrays.asList(Role.ROLE_TRAVELER));
    }
}
