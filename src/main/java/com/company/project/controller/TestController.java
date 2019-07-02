package com.company.project.controller;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@PreAuthorize("hasRole('ROLE_TEST')")
public class TestController {

    @GetMapping("product")
    public Result product(){
        return ResultGenerator.genSuccessResult("productId: test001");
    }
}
